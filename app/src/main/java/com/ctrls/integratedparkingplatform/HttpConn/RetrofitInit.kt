package com.ctrls.integratedparkingplatform.HttpConn

import android.util.Log
import com.ctrls.integratedparkingplatform.VOModel.TestListModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInit {

    val retrofit: Retrofit
        get() = Retrofit.Builder()
            .baseUrl("https://ctrls-studio.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val networkService: INetworkService = retrofit.create(INetworkService::class.java)
    val userListModel = networkService.doGetTest()
//    val testListCall: Call<TestListModel> = init.userListModel

}