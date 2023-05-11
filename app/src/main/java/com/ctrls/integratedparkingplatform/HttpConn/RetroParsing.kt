package com.ctrls.integratedparkingplatform.HttpConn

import android.util.Log
import com.ctrls.integratedparkingplatform.VOModel.TestListModel
import com.ctrls.integratedparkingplatform.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetroParsing {
    private val retrofitInit = RetrofitInit()
    private val testListModel : ArrayList<TestListModel> = ArrayList()
    lateinit var binding:ActivityMainBinding
    fun bindingInit() {

    }
    fun onRetroParsing(callback: (ArrayList<TestListModel>) -> Unit) {
        val call = retrofitInit.iipListModel
        call.enqueue(object : Callback<List<TestListModel>> {
            override fun onResponse(
                call: Call<List<TestListModel>>,
                response: Response<List<TestListModel>>
            ) {
                if (response.isSuccessful) {
                    val testList = response.body()?.getOrNull(0)
                    if (testList != null) {
                        testListModel.add(testList)
                        Log.d("INFO_TAG", testList.value1)
                    } else {
                        Log.e("ERROR_TAG", "Test list is null")
                    }
                } else {
                    Log.e("ERROR_TAG", "Response error: ${response.code()}")
                }

                callback(testListModel)
            }

            override fun onFailure(call: Call<List<TestListModel>>, t: Throwable) {
                call.cancel()
                Log.e("ERROR_TAG", "Retrofit failure: ${t.message}")
                callback(ArrayList()) // 실패 시 빈 리스트 반환
            }
        })
    }

    /*fun onRetroSend(callback: (ArrayList<TestListModel>) -> Unit) {
        val call = retrofitInit.iipSendModel
        call.enqueue(object : Callback<List<TestListModel>>{
            override fun onResponse(
                call: Call<List<TestListModel>>,
                response: Response<List<TestListModel>>
            ) {

                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<List<TestListModel>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }*/
}
