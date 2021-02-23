package com.example.beaglepoc.beagle

import android.app.Application
import br.com.zup.beagle.scaffold.BeagleScaffold

class AppApplication: Application() {
    override fun onCreate() {
        super.onCreate()
       // BeagleSetup().init(this
        BeagleScaffold(BeagleSetup()).init(this)
    }
}