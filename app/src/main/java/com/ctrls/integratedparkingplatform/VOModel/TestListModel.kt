package com.ctrls.integratedparkingplatform.VOModel

import com.google.gson.annotations.SerializedName

data class TestListModel(
    var id:String,
    @SerializedName("app_code")
    var appCode:String,
    @SerializedName("car_value")
    var value1:String,
    @SerializedName("barrier_value")
    var value2:String,
    @SerializedName("temp_value")
    var value3:String,
    @SerializedName("humi_value")
    var value4:String,
    @SerializedName("uptime")
    var upTime:String
)
