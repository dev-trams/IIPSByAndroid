package com.ctrls.integratedparkingplatform.HttpConn

import android.util.Log
import com.ctrls.integratedparkingplatform.VOModel.TestListModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInit {
    /*val CAR_VALUE: String
        get() {
            TODO()
        }
    val BARRIER_VALUE: String
        get() {
            TODO()
        }
    val TEMP_VALUE: String
        get() {
            TODO()
        }
    val HUMI_VALUE: String
        get() {
            TODO()
        }*/

    val retrofit: Retrofit
        get() = Retrofit.Builder()
            .baseUrl("http://34.64.163.160/db.php/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val networkService: INetworkService = retrofit.create(INetworkService::class.java)
    val iipListModel = networkService.doGetTest()
//    val iipSendModel = networkService.sendData();
    /*val iipSendModel =
        networkService.sendData(
            "iipTest",
            CAR_VALUE,
            BARRIER_VALUE,
            TEMP_VALUE,
            HUMI_VALUE
        )*/

    //    val testListCall: Call<TestListModel> = init.userListModel
}