package com.ctrls.integratedparkingplatform.Utils

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.ctrls.integratedparkingplatform.Layouts.ParkingMembraneActivity
import com.ctrls.integratedparkingplatform.databinding.ActivityMainBinding

class Technology {
    fun technology() {

    }

    lateinit var binding: ActivityMainBinding

    private var mHandler = android.os.Handler()

    fun delay(r:Runnable, delayMillis: Long) {
        mHandler.postDelayed(r, delayMillis)
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
    fun onDisConnectingChecked(
        code: String,
        value: String,
        progressBar: ProgressBar,
        textView: TextView,
        commit: String
    ) : String {
        if (!(value.equals("") || value.isEmpty())) {
            progressBar.isIndeterminate = false
            progressBar.visibility = View.GONE
            textView.text = value
        } else {
            progressBar.isIndeterminate = true
            progressBar.visibility = View.VISIBLE
            textView.text = commit
            return "\n[$code]에 데이터가 없습니다. ERROR 102"
        }
        return ""
    }

    /**
     * Activity 이동(Intent) 클래스
     *
     * movingClass 에 null을 입력 시 Log만 작동함
     * @param view View 클래스(Button, Layout...)
     * @param startClass 출발할 클래스
     * @param movingClass 이동할 클래스
     */
    fun goActivity(view: View, startClass: Context, movingClass: Class<*>?) {
        var intent : Intent = Intent()
        view.setOnClickListener {
            if (movingClass != null) {
                intent = Intent(startClass, movingClass)
                startClass.startActivity(intent)
            }
        }

    }
}