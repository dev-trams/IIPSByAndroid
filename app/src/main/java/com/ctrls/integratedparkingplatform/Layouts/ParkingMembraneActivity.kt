package com.ctrls.integratedparkingplatform.Layouts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ctrls.integratedparkingplatform.R
import com.ctrls.integratedparkingplatform.databinding.ActivityParkingMembraneBinding

class ParkingMembraneActivity : AppCompatActivity() {
    lateinit var binding:ActivityParkingMembraneBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParkingMembraneBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}