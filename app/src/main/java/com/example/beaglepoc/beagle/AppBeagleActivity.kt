package com.example.beaglepoc.beagle

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import br.com.zup.beagle.android.annotation.BeagleComponent
import br.com.zup.beagle.android.view.BeagleActivity
import br.com.zup.beagle.android.view.ServerDrivenState
import com.example.beaglepoc.R


@BeagleComponent
class AppBeagleActivity : BeagleActivity() {

    private val progressBar: ProgressBar by lazy { findViewById<ProgressBar>(R.id.progress_bar) }
    private val mToolbar: Toolbar by lazy { findViewById<Toolbar>(R.id.toolbar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_beagle_activity)

    }

    override fun getServerDrivenContainerId(): Int = R.id.server_driven_container
    override fun getToolbar(): Toolbar = mToolbar

    override fun onServerDrivenContainerStateChanged(state: ServerDrivenState) {
        if (state is ServerDrivenState.Loading) {
            progressBar.visibility = if(state.loading) View.VISIBLE else View.GONE
        }else if (state is ServerDrivenState.Error) {
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
        }
//        when (state) {
//            ServerDrivenState.Started -> progressBar.visibility = View.VISIBLE
//            ServerDrivenState.Finished -> progressBar.visibility = View.GONE
//            is ServerDrivenState.Error -> {
//                Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
//            }
//            else -> progressBar.visibility = View.GONE
//        }
    }
}