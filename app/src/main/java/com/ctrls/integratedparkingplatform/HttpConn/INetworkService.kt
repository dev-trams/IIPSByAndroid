package com.ctrls.integratedparkingplatform.HttpConn


import com.ctrls.integratedparkingplatform.VOModel.TestListModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface INetworkService {
    // @GET : 서버 연동 시 GET 방식으로 하겠다는 의미
    // @Query : 서버에 전달되는 데이터
    // @Url : 요청 URL
    @GET("db.php")
    fun doGetTest(): Call<List<TestListModel>>

    /**
     * sendData() 서버에 데이터를 보내기 위한 인터페이스
     * @return data.php
     * */
    @GET("data.php?")
    fun sendData(@Query("app_code") app_code:String,
            @Query("car_value") car_value:String,
            @Query("barrier_value") barrier_value:String,
            @Query("temp_value") temp_value:String,
            @Query("humi_value") humi_value:String,
    ): Call<List<TestListModel>>

}