package com.ctrls.integratedparkingplatform.Layouts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ctrls.integratedparkingplatform.HttpConn.RetroParsing
import com.ctrls.integratedparkingplatform.R
import com.ctrls.integratedparkingplatform.Utils.Utils
import com.ctrls.integratedparkingplatform.databinding.ActivityParkingIndoreClimateBinding

class ParkingIndoreClimateActivity : AppCompatActivity() {
    lateinit var binding:ActivityParkingIndoreClimateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParkingIndoreClimateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onRetrofitParsing()

    }

    fun onRetrofitParsing() {
        val retroParsing: RetroParsing = RetroParsing()
        val utils:Utils = Utils()

        retroParsing.onRetroParsing { testListModels ->
            if (!testListModels.isEmpty()) {
                binding.viewPowerState1.setImageDrawable(getDrawable(R.drawable.power_icon_radius_on))
                val indoreValue = testListModels[0].value2
                Log.d("INFO-ERROR", "test")

                if(indoreValue.equals("Y")) {
                    binding.viewIndoreImg.setImageDrawable(getDrawable(R.drawable.indore_climate_activity))
                } else if(indoreValue.equals("N")) {
                    binding.viewIndoreImg.setImageDrawable(getDrawable(R.drawable.indore_climate_default))
                }
            } else {
                binding.viewPowerState1.setImageDrawable(getDrawable(R.drawable.power_icon_radius_off))
            }
        }
        Log.d("INFO-ERROR", "test")
        utils.technology.delay({onRetrofitParsing()}, 2000L)
    }
}