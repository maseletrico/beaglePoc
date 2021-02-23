package com.example.beaglepoc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.zup.beagle.android.view.BeagleActivity
import br.com.zup.beagle.android.view.ScreenRequest
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        beagle_button.setOnClickListener {
            startActivity(BeagleActivity.newIntent(this, ScreenRequest("/screen")))
//            val intent = this.newServerDrivenIntent<BeagleActivity>(ScreenRequest("/screen"))
//            startActivity(intent)
//            finish()
        }

    }
}