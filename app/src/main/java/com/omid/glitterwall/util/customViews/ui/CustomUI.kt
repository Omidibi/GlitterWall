package com.omid.glitterwall.util.customViews.ui

import android.graphics.LinearGradient
import android.graphics.Shader
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.coroutineScope
import com.omid.glitterwall.databinding.FragmentHomeBinding
import com.omid.glitterwall.ui.main.home.HomeFragment
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine

class CustomUI {
    companion object {
        fun customUI(fragment: HomeFragment, binding: FragmentHomeBinding) {
            binding.apply {
                fragment.lifecycle.coroutineScope.launch {
                    txtLatest.awaitLayoutChange()
                    txtLatest.setGradientTextColor(
                        com.omid.glitterwall.R.color.torchRed,
                        com.omid.glitterwall.R.color.radicalRed,
                        com.omid.glitterwall.R.color.Cerise,
                        com.omid.glitterwall.R.color.torchRed,
                        com.omid.glitterwall.R.color.radicalRed,
                        com.omid.glitterwall.R.color.Cerise,
                    )
                }

                fragment.lifecycle.coroutineScope.launch {
                    txtFeatured.awaitLayoutChange()
                    txtFeatured.setGradientTextColor(
                        com.omid.glitterwall.R.color.torchRed,
                        com.omid.glitterwall.R.color.radicalRed,
                        com.omid.glitterwall.R.color.Cerise,
                        com.omid.glitterwall.R.color.torchRed,
                        com.omid.glitterwall.R.color.radicalRed,
                        com.omid.glitterwall.R.color.Cerise,
                    )
                }
            }
        }

        private fun TextView.setGradientTextColor(vararg colorRes: Int) {
            val floatArray = ArrayList<Float>(colorRes.size)
            for (i in colorRes.indices) {
                floatArray.add(i, i.toFloat() / (colorRes.size - 1))
            }
            // horizontal
            val textShader: Shader = LinearGradient(0f, 0f,
                this.width.toFloat(), 0f,
                colorRes.map { ContextCompat.getColor(context, it) }.toIntArray(),
                floatArray.toFloatArray(),
                Shader.TileMode.CLAMP
            )
            this.paint.shader = textShader
        }

       /* private fun TextView.setGradientTextColor(vararg colorRes: Int) {
            val floatArray = ArrayList<Float>(colorRes.size)
            for (i in colorRes.indices) {
                floatArray.add(i, i.toFloat() / (colorRes.size - 1))
            }
            // vertical
            val textShader: Shader = LinearGradient(0f, 0f, 0f,
                this.height.toFloat(),
                colorRes.map { ContextCompat.getColor(context, it) }.toIntArray(),
                floatArray.toFloatArray(),
                Shader.TileMode.CLAMP
            )
            this.paint.shader = textShader
        }*/

        private suspend fun View.awaitLayoutChange() = suspendCancellableCoroutine { cont ->
            val listener = object : View.OnLayoutChangeListener {
                override fun onLayoutChange(view: View?, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int) {
                    view?.removeOnLayoutChangeListener(this)
                    cont.resumeWith(Result.success(Unit))
                }
            }
            addOnLayoutChangeListener(listener)
            cont.invokeOnCancellation { removeOnLayoutChangeListener(listener) }
        }
    }
}