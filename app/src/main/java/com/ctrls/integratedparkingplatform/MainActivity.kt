package com.ctrls.integratedparkingplatform

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.ctrls.integratedparkingplatform.HttpConn.RetrofitInit
import com.ctrls.integratedparkingplatform.Layouts.ParkingIndoreClimateActivity
import com.ctrls.integratedparkingplatform.Layouts.ParkingInfoActivity
import com.ctrls.integratedparkingplatform.Layouts.ParkingMembraneActivity
import com.ctrls.integratedparkingplatform.Utils.Utils
import com.ctrls.integratedparkingplatform.VOModel.TestListModel

import com.mikhaellopez.circularprogressbar.CircularProgressBar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var container_view_1:ConstraintLayout
    private lateinit var container_view_2:LinearLayout
    private lateinit var container_view_3:RelativeLayout

    private lateinit var progressBar: CircularProgressBar
    private lateinit var progressBarStateValue2_1 : ProgressBar
    private lateinit var progressBarStateValue2_2 : ProgressBar
    private lateinit var textView: TextView
    private lateinit var textViewValue2_1: TextView
    private lateinit var textViewValue2_2: TextView
    private lateinit var viewIndoreState:ImageView
    private lateinit var textViewIndore:TextView
    private lateinit var progressBarStateValue3: ProgressBar

    private val mHandler = Handler()

//    private val utils = Utils()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        onIdByInit()

        goActivity(container_view_1, ParkingInfoActivity::class.java)
        goActivity(container_view_2, ParkingMembraneActivity::class.java)
        goActivity(container_view_3, ParkingIndoreClimateActivity::class.java)
        retrofit()

    }

    /**
     * Activity 이동(Intent) 클래스
     *
     * movingClass 에 null을 입력 시 Log만 작동함
     * @param view View 클래스(Button, Layout...)
     * @param movingClass 이동할 클래스
     */
    private fun goActivity(view:View, movingClass: Class<*>?) {
        view.setOnClickListener {
            Toast.makeText(this@MainActivity, "click", Toast.LENGTH_SHORT).show()
            if (movingClass != null) {
                val intent = Intent(this@MainActivity, movingClass)
                startActivity(intent)
            }
        }
    }
    private fun retrofit() {
        val retrofitInit = RetrofitInit()
        val call = retrofitInit.userListModel
        call.enqueue(object : Callback<List<TestListModel>> {
            override fun onResponse(call: Call<List<TestListModel>>, response: Response<List<TestListModel>>) {
                if (response.isSuccessful) {
                    val testList = response.body()?.getOrNull(0)
                    if (testList != null) {
                        progressBar.progress = testList.value1.toFloat()
                        val progressValue = progressBar.progress
                        val showProgressValue = "$progressValue %"
                        textView.text = showProgressValue
                        onDisConnectingChecked(testList.value3, progressBarStateValue2_1, textViewValue2_1, "온도")
                        onDisConnectingChecked(testList.value4, progressBarStateValue2_2, textViewValue2_2, "습도")
                        onDisConnectingChecked(testList.value2, progressBarStateValue3, textViewIndore, "차수막 상태")
                        if (testList.value2 == "Y") {
                            viewIndoreState.setImageDrawable(getDrawable(R.drawable.indore_climate_activity))
                            viewIndoreState.visibility = View.VISIBLE
                        } else if(testList.value2 == "N") {
                            viewIndoreState.setImageDrawable(getDrawable(R.drawable.indore_climate_default))
                            viewIndoreState.visibility = View.VISIBLE
                            textViewIndore.visibility = View.GONE
                        }
                        Log.d("INFO_TAG", testList.value1)
                    } else {
                        Log.e("ERROR_TAG", "Test list is null")
                    }
                } else {
                    Log.e("ERROR_TAG", "Response error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<TestListModel>>, t: Throwable) {
                call.cancel()
                Log.e("ERROR_TAG", "Retrofit failure: ${t.message}")
            }
        })
        mHandler.postDelayed({ retrofit() }, 2000L)
    }

    /**
     * 온도, 습도 공백, Null 체크 함수
     *
     * @return [공백, Null 체크 후 해당 하면 프로그래스 표시]
     *
     * or [해당 하지 않는 다면 값 갱신]
     * @param value 서버 에서 가져온 데이터
     * @param progressBar 값이 없을 시 나오는 ProgressBar
     * @param textView 값이 없을 시 나오는 TextView
     * @param commit 값이 없을 시 나오는 문자(온도or습도)
     * */
    fun onDisConnectingChecked(value: String, progressBar: ProgressBar, textView: TextView, commit:String) {
        if(!(value.equals("") || value.isEmpty())){
            progressBar.isIndeterminate = false
            progressBar.visibility = View.GONE
            textView.text = value
        }else {
            progressBar.isIndeterminate = true
            progressBar.visibility = View.VISIBLE
            textView.text = commit
        }
    }
    fun onIdByInit() {
        container_view_1 = findViewById(R.id.container_view_1)
        container_view_2 = findViewById(R.id.container_view_2)
        container_view_3 = findViewById(R.id.container_view_3)

        progressBar = findViewById(R.id.progress_parking_state)
        progressBarStateValue2_1 = findViewById(R.id.progress_parking_state2_1)
        progressBarStateValue2_2 = findViewById(R.id.progress_parking_state2_2)
        progressBarStateValue3 = findViewById(R.id.progress_parking_state3)

        textView = findViewById(R.id.view_parking_state)
        textViewValue2_1 = findViewById(R.id.text_view_temp)
        textViewValue2_2 = findViewById(R.id.text_view_humi)
        textViewIndore = findViewById(R.id.text_view_indore)

        viewIndoreState = findViewById(R.id.view_indore_state)
    }

}