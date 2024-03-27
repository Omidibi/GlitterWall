package com.omid.glitterwall.fragments.showImageFragment

import android.Manifest
import android.app.DownloadManager
import android.app.WallpaperManager
import android.content.ContentValues
import android.content.Context
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.Environment.getExternalStoragePublicDirectory
import android.provider.MediaStore
import android.support.annotation.RequiresApi
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.omid.glitterwall.HomeWidget
import com.omid.glitterwall.R
import com.omid.glitterwall.databinding.FragmentShowImageBinding
import com.omid.glitterwall.models.AllVideo
import java.io.File
import java.io.IOException

class ShowImageFragment : Fragment() {
    private lateinit var binding: FragmentShowImageBinding
    private lateinit var showImgViewModel: ShowImgViewModel
    private lateinit var allVideo: AllVideo

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    private var storagePermissions33 = arrayOf(Manifest.permission.READ_MEDIA_IMAGES)
    private var storagePermissions = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        getData()
        setupBindingAndViewModel()
        return binding.root
    }

    @androidx.annotation.RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickEvent()
        savePhotoInDatabase()
    }

    private fun getData(){
        allVideo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireArguments().getParcelable("allVideo", AllVideo::class.java)!!
        } else {
            requireArguments().getParcelable("allVideo")!!
        }
    }

    private fun setupBindingAndViewModel() {
        requireActivity().requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        binding = FragmentShowImageBinding.inflate(layoutInflater)
        binding.apply {
            showImgViewModel = ViewModelProvider(this@ShowImageFragment)[ShowImgViewModel::class.java]
            Glide.with(requireContext())
                .load(allVideo.videoThumbnailB)
                .placeholder(R.drawable.coming)
                .error(R.drawable.error2)
                .into(img)
            if (showImgViewModel.search(allVideo.id)){
                Glide.with(requireContext()).load(R.drawable.favorite).into(fvt)
            }else {
                Glide.with(requireContext()).load(R.drawable.favorite1).into(fvt)
            }
        }
    }

    @androidx.annotation.RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun clickEvent() {
        binding.apply {

            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
                findNavController().popBackStack()
                HomeWidget.bnv.visibility = View.VISIBLE
                HomeWidget.toolbar.visibility = View.VISIBLE
            }

            btnDownload.setOnClickListener {
                downloadImg()
            }

            btnSetWallpaper.setOnClickListener {
                setWallpaper()
            }
        }
    }

    private fun savePhotoInDatabase() {
        binding.apply {
            fvt.setOnClickListener {
                showImgViewModel.saveOrDelete(allVideo)
                if (showImgViewModel.search(allVideo.id)){
                    Glide.with(requireContext()).load(R.drawable.favorite).into(fvt)
                }else {
                    Glide.with(requireContext()).load(R.drawable.favorite1).into(fvt)
                }
            }
        }
    }

    @androidx.annotation.RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun downloadImg() {
        /** روش دانلود برای نسخه 13 به بعد اندروید*/
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.S) {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.READ_MEDIA_IMAGES
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(requireActivity(), storagePermissions33, 1)
            } else {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.start_downloading),
                    Toast.LENGTH_LONG
                ).show()
                Glide.with(this)
                    .asBitmap()
                    .load(allVideo.videoUrl)
                    .into(object : CustomTarget<Bitmap>() {
                        override fun onResourceReady(
                            resource: Bitmap,
                            transition: Transition<in Bitmap>?
                        ) {
                            val resolver = requireContext().contentResolver
                            val contentValues = ContentValues().apply {
                                put(MediaStore.MediaColumns.DISPLAY_NAME, "${allVideo.id}.jpg")
                                put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                                put(
                                    MediaStore.MediaColumns.RELATIVE_PATH,
                                    Environment.DIRECTORY_PICTURES + "/GlitterWall"
                                )
                            }
                            val uri = resolver.insert(
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                contentValues
                            )
                            val outputStream = resolver.openOutputStream(uri!!)
                            if (outputStream != null) {
                                resource.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                            }
                            outputStream?.close()
                            Toast.makeText(
                                requireContext(),
                                getString(R.string.download_completed),
                                Toast.LENGTH_LONG
                            ).show()
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {

                        }
                    })
            }

        } else {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(requireActivity(), storagePermissions, 1)
            } else {
                /** روش دانلود برای نسخه 10 اندروید*/
                if (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q) {
                    val request = DownloadManager.Request(Uri.parse(allVideo.videoUrl))
                        .setTitle(requireActivity().title)
                        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                        .setDestinationInExternalFilesDir(
                            requireContext(),
                            Environment.DIRECTORY_PICTURES,
                            "${allVideo.id}.jpg"
                        )
                        .setAllowedOverMetered(true)
                        .setAllowedOverRoaming(true)
                    val downloadManager =
                        requireContext().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                    downloadManager.enqueue(request)
                } else {
                    /** روش دانلود برای دیگر نسخه های اندروید*/
                    val request = DownloadManager.Request(Uri.parse(allVideo.videoUrl))
                        .setTitle(requireActivity().title)
                        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                        .setDestinationInExternalPublicDir(
                            Environment.DIRECTORY_PICTURES,
                            "/GlitterWall/${allVideo.id}.jpg"
                        )
                    val downloadManager =
                        requireContext().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                    downloadManager.enqueue(request)
                }
            }
        }
    }

    private fun setWallpaper() {
        /**اگر اندروید بالاتر از 12 بود */
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.S) {
            /**ایجاد SharedPreferences برای ذخیره وضعیت بودن عکس در والپیپر */
            val prefs = requireContext().getSharedPreferences("wallpaper", Context.MODE_PRIVATE)
            val editor = prefs.edit()

            /**گرفتن آدرس عکس دانلود شده ی موجود در حافظه */
            val filePath =
                getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString() + "/GlitterWall/" + allVideo.id + ".jpg"
            val file = File(filePath)
            /**چک کردن موجودی همچین فایلی در حاغظه */
            if (file.exists()) {
                /**دادن فایل به متد decodeFile در صورت بودن */
                val bitmap = BitmapFactory.decodeFile(filePath)
                /**اگر عکسه دانلود شده به درستی و بدون مشکل دانلود شده بود و وجود داشت و قابل خواندن توسط bitmap بود(برابر با نال نبود)  */
                if (bitmap != null) {
                    /**ایجاد یک متغیر ازWallpaperManager */
                    val wallpaperManager = WallpaperManager.getInstance(requireContext())
                    /**انجام بده */
                    try {
                        /**چک کردن وضعیت بودن عکس در والپیپر */
                        val currentWallpaperId = prefs.getString("currentWallpaperId", "")
                        /**اگر عکس در والپیپر بود */
                        if (currentWallpaperId == allVideo.id) {
                            Toast.makeText(
                                requireContext(),
                                getString(R.string.set_now),
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            /**اگر عکس در والپیپر نبود آن را ست کند */
                            wallpaperManager.setBitmap(bitmap)
                            Toast.makeText(
                                requireContext(),
                                getString(R.string.set_done),
                                Toast.LENGTH_LONG
                            ).show()
                            /**ذخیره وضعیت عکس موجود در والپیپر */
                            editor.putString("currentWallpaperId", allVideo.id)
                            editor.apply()
                        }
                    } catch (e: Exception) {
                        /**مشکل در ست کردن : میتواند به هر دلیلی باشد(خطاا توسط متغیر e چک شود) */
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.set_problem),
                            Toast.LENGTH_LONG
                        ).show()
                        Log.e("", "")
                    }
                } else {
                    /**اگر عکسه مورد نظر به درستی دانلود نشده بود و وجود نداشت و یا قابل خواندن توسط bitmap نبود(برابر با نال بود)  */
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.not_found),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                /**چک کردن موجودی همچین فایلی در حاغظه ->> اگر وجود نداشت */
                Toast.makeText(
                    requireContext(),
                    getString(R.string.download_first),
                    Toast.LENGTH_LONG
                ).show()
            }
        } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q) {
            /**اگر اندروید برابر با 10 بود */
            /**ایجاد SharedPreferences برای ذخیره وضعیت بودن عکس در والپیپر */
            val prefs = requireContext().getSharedPreferences("wallpaper", Context.MODE_PRIVATE)
            val editor = prefs.edit()

            /**گرفتن آدرس عکس دانلود شده ی موجود در حافظه */
            val file =
                File(requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES), "${allVideo.id}.jpg")
            /**چک کردن موجودی همچین فایلی در حاغظه */
            if (file.exists()) {
                /**دادن فایل به متد decodeFile در صورت بودن */
                val bitmap = BitmapFactory.decodeFile(file.absolutePath)
                /**اگر عکسه دانلود شده به درستی و بدون مشکل دانلود شده بود و وجود داشت و قابل خواندن توسط bitmap بود(برابر با نال نبود)  */
                if (bitmap != null) {
                    /**ایجاد یک متغیر ازWallpaperManager */
                    val wallpaperManager = WallpaperManager.getInstance(requireContext())
                    /**انجام بده */
                    try {
                        /**چک کردن وضعیت بودن عکس در والپیپر */
                        val currentWallpaperId = prefs.getString("currentWallpaperId", "")
                        /**اگر عکس در والپیپر بود */
                        if (currentWallpaperId == allVideo.id) {
                            Toast.makeText(
                                requireContext(),
                                getString(R.string.set_now),
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            /**اگر عکس در والپیپر نبود آن را ست کند */
                            wallpaperManager.setBitmap(bitmap)
                            Toast.makeText(
                                requireContext(),
                                getString(R.string.set_done),
                                Toast.LENGTH_LONG
                            ).show()
                            /**ذخیره وضعیت عکس موجود در والپیپر */
                            editor.putString("currentWallpaperId", allVideo.id)
                            editor.apply()
                        }
                    } catch (ex: IOException) {
                        /**مشکل در ست کردن : میتواند به هر دلیلی باشد(خطاا توسط متغیر e چک شود) */
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.set_problem),
                            Toast.LENGTH_LONG
                        ).show()
                        ex.printStackTrace()
                        Log.e("", "")
                    }
                } else {
                    /**اگر عکسه مورد نظر به درستی دانلود نشده بود و وجود نداشت و یا قابل خواندن توسط bitmap نبود(برابر با نال بود)  */
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.not_found),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                /**چک کردن موجودی همچین فایلی در حاغظه ->> اگر وجود نداشت */
                Toast.makeText(
                    requireContext(),
                    getString(R.string.download_first),
                    Toast.LENGTH_LONG
                ).show()
            }
        } else {
            /**ست برای دیگر نسخه های اندروید */
            /**ایجاد SharedPreferences برای ذخیره وضعیت بودن عکس در والپیپر */
            val prefs = requireContext().getSharedPreferences("wallpaper", Context.MODE_PRIVATE)
            val editor = prefs.edit()

            /**گرفتن آدرس عکس دانلود شده ی موجود در حافظه */
            val filePath =
                getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString() + "/GlitterWall/" + allVideo.id + ".jpg"
            val file = File(filePath)
            /**چک کردن موجودی همچین فایلی در حاغظه */
            if (file.exists()) {
                /**دادن فایل به متد decodeFile در صورت بودن */
                val bitmap = BitmapFactory.decodeFile(filePath)
                /**اگر عکسه دانلود شده به درستی و بدون مشکل دانلود شده بود و وجود داشت و قابل خواندن توسط bitmap بود(برابر با نال نبود)  */
                if (bitmap != null) {
                    /**ایجاد یک متغیر ازWallpaperManager */
                    val wallpaperManager = WallpaperManager.getInstance(requireContext())
                    /**انجام بده */
                    try {
                        /**چک کردن وضعیت بودن عکس در والپیپر */
                        val currentWallpaperId = prefs.getString("currentWallpaperId", "")
                        /**اگر عکس در والپیپر بود */
                        if (currentWallpaperId == allVideo.id) {
                            Toast.makeText(
                                requireContext(),
                                getString(R.string.set_now),
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            /**اگر عکس در والپیپر نبود آن را ست کند */
                            wallpaperManager.setBitmap(bitmap)
                            Toast.makeText(
                                requireContext(),
                                getString(R.string.set_done),
                                Toast.LENGTH_LONG
                            ).show()
                            /**ذخیره وضعیت عکس موجود در والپیپر */
                            editor.putString("currentWallpaperId", allVideo.id)
                            editor.apply()
                        }

                    } catch (e: Exception) {
                        /**مشکل در ست کردن : میتواند به هر دلیلی باشد(خطاا توسط متغیر e چک شود) */
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.set_problem),
                            Toast.LENGTH_LONG
                        ).show()
                        Log.e("", "")
                    }
                } else {
                    /**اگر عکسه مورد نظر به درستی دانلود نشده بود و وجود نداشت و یا قابل خواندن توسط bitmap نبود(برابر با نال بود)  */
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.not_found),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                /**چک کردن موجودی همچین فایلی در حاغظه ->> اگر وجود نداشت */
                Toast.makeText(
                    requireContext(),
                    getString(R.string.download_first),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}