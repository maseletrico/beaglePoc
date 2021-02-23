package com.example.beaglepoc.beagle

import br.com.zup.beagle.android.annotation.BeagleComponent
import br.com.zup.beagle.android.setup.DesignSystem
import com.example.beaglepoc.R

@BeagleComponent
class AppDesignSystem : DesignSystem() {

    override fun toolbarStyle(id: String): Int {
        return when(id) {
            "Toolbar.TitleTextBold" -> R.style.Toolbar_TitleTextBold
            "Toolbar.DefaultTitleText" -> R.style.Toolbar_DefaultTitleText
            else -> R.style.Toolbar_DefaultTitleText
        }
    }

//    override fun image(name: String): Int{
//        return when(name){
//            ""
//        }
//    }

    override fun textStyle(id: String): Int {
        return when (id) {
            "defaultTextStyle" -> R.style.CustomDefaultText
            "defaultTexBoldStyle" -> R.style.CustomDefaultTextBold
            "largeTexStyle" -> R.style.CustomLargeText
            "largeTexBoldStyle" -> R.style.CustomLargeText
            "xLargeTexStyle" -> R.style.CustomXlargeText
            "xLargeTexBoldStyle" -> R.style.CustomXlargeTextBold
            else -> R.style.CustomDefaultText
        }
    }
}