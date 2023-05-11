package com.ctrls.integratedparkingplatform.Layouts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ctrls.integratedparkingplatform.R
import com.ctrls.integratedparkingplatform.databinding.ActivityParkingInfoBinding

class ParkingInfoActivity : AppCompatActivity() {
    lateinit var binding: ActivityParkingInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParkingInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}