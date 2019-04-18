package com.smk.yjkotlindemo.net.bean

import com.google.gson.annotations.SerializedName

/**
 * Created on 2019/4/11 13:54
 * .
 *
 * @author yj
 * @org 浙江房超信息科技有限公司
 */
data class NewBean(
    @SerializedName("title") var title: String?,
    @SerializedName("time") var time: String?,
    @SerializedName("src") var src: String?,
    @SerializedName("pic") var pic: String?,
    @SerializedName("weburl") var weburl: String?
)