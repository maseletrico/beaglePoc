package com.example.beaglepoc.beagle

import br.com.zup.beagle.android.annotation.BeagleComponent
import br.com.zup.beagle.android.setup.BeagleConfig
import br.com.zup.beagle.android.setup.Cache
import br.com.zup.beagle.android.setup.Environment
import br.com.zup.beagle.scaffold.BeagleConfigScaffold
import com.example.beaglepoc.const.Constants

@BeagleComponent
class AppBeagleConfig : BeagleConfigScaffold() {
    override val baseUrl: String get() = Constants.BASE_URL
//    override val cache: Cache
//        get() = Cache(
//            enabled = true, // If true, we will cache data on disk and memory.
//            maxAge = Constants.MAX_AGE, // Time in seconds that memory cache will live.
//            size = Constants.CACHE_SIZE
//        )
//    override val environment: Environment get() = Environment.DEBUG
//    override val isLoggingEnabled: Boolean = true


}