package com.ctrls.integratedparkingplatform

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.ctrls.integratedparkingplatform.HttpConn.RetroParsing
import com.ctrls.integratedparkingplatform.Layouts.ParkingIndoreClimateActivity
import com.ctrls.integratedparkingplatform.Layouts.ParkingInfoActivity
import com.ctrls.integratedparkingplatform.Layouts.ParkingMembraneActivity
import com.ctrls.integratedparkingplatform.Utils.Utils
import com.ctrls.integratedparkingplatform.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val mHandler = Handler()
    lateinit var binding: ActivityMainBinding

//    private val utils = Utils()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val util: Utils = Utils()

        util.technology.goActivity(binding.containerView1, this@MainActivity, ParkingInfoActivity::class.java)
        util.technology.goActivity(binding.containerView2, this@MainActivity, ParkingMembraneActivity::class.java)
        util.technology.goActivity(binding.containerView3, this@MainActivity, ParkingIndoreClimateActivity::class.java)

        retrofit()

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun retrofit() {
        val parsing: RetroParsing = RetroParsing()
        val util: Utils = Utils()
        parsing.onRetroParsing { testListModels ->
            if (testListModels.isEmpty()) {
                binding.viewLogSystem.append("\n 데이터가 없습니다.")
            } else {
                binding.progressParkingState.progress = testListModels[0].value1.toFloat()
                val progressValue = binding.progressParkingState.progress
                val showProgressValue = "$progressValue %"
                binding.viewParkingState.text = showProgressValue
                binding.viewLogSystem.append(
                    util.technology.onDisConnectingChecked(
                        "temp",
                        testListModels[0].value3,
                        binding.progressParkingState21,
                        binding.textViewTemp,
                        "온도"
                    )
                )
                binding.viewLogSystem.append(
                    util.technology.onDisConnectingChecked(
                        "humi",
                        testListModels[0].value4,
                        binding.progressParkingState22,
                        binding.textViewHumi,
                        "습도"
                    )
                )
                binding.viewLogSystem.append(
                    util.technology.onDisConnectingChecked(
                        "indore",
                        testListModels[0].value2,
                        binding.progressParkingState3,
                        binding.textViewIndore,
                        "차수막 상태"
                    )
                )
                if (testListModels[0].value2 == "Y") {
                    binding.viewIndoreState.setImageDrawable(getDrawable(R.drawable.indore_climate_activity))
                    binding.viewIndoreState.visibility = View.VISIBLE
                } else if (testListModels[0].value2 == "N") {
                    binding.viewIndoreState.setImageDrawable(getDrawable(R.drawable.indore_climate_default))
                    binding.viewIndoreState.visibility = View.VISIBLE
                    binding.textViewIndore.visibility = View.GONE
                }
                binding.viewLogSystem.append("\n 데이터가 갱신되었습니다.")
            }
        }
        binding.layoutScrollLog.post { binding.layoutScrollLog.fullScroll(View.FOCUS_DOWN) }
        mHandler.postDelayed({ retrofit() }, 2000L)
    }
}