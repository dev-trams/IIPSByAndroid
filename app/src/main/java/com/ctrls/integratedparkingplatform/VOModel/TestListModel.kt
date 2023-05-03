package com.ctrls.integratedparkingplatform.VOModel

import com.google.gson.annotations.SerializedName

data class TestListModel(
    var id:String,
    @SerializedName("app_code")
    var appCode:String,
    var value1:String,
    var value2:String,
    var value3:String,
    var value4:String,
    var upTime:String
)
