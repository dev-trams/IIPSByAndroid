package com.ctrls.integratedparkingplatform.HttpConn


import com.ctrls.integratedparkingplatform.VOModel.TestListModel
import retrofit2.Call
import retrofit2.http.GET

interface INetworkService {
    // @GET : 서버 연동 시 GET 방식으로 하겠다는 의미
    // @Query : 서버에 전달되는 데이터
    // @Url : 요청 URL
    @GET("db.php")
    fun doGetTest(): Call<List<TestListModel>>
}