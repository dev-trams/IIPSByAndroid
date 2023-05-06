package com.ctrls.integratedparkingplatform.Utils

import java.util.logging.Handler

class Technology {
    var technology = Technology()
    private var mHandler = android.os.Handler()

    fun delay(r:Runnable, delayMillis: Long) {
        mHandler.postDelayed(r, delayMillis)
    }
}