package com.omid.glitterwall.ui.customViews.customFonts

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.omid.glitterwall.utils.configuration.AppConfiguration

class IranSansTextView : AppCompatTextView {
    constructor(context: Context?) : super(context!!) {
        extracted()
    }

    constructor(context: Context?, attrs: AttributeSet) : super(context!!, attrs) {
        extracted()
    }

    constructor(context: Context?, attrs: AttributeSet, defStyleAttr: Int) : super(context!!, attrs, defStyleAttr) {
        extracted()
    }

    private fun extracted() {
        val typeface = Typeface.createFromAsset(AppConfiguration.getContext().assets, "Fonts/IRANSans/iran_sans_mobile.ttf")
        setTypeface(typeface)
    }
}