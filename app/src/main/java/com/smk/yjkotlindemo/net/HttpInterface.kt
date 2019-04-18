package com.smk.yjkotlindemo.net

import com.smk.yjkotlindemo.net.bean.BaseParser
import com.smk.yjkotlindemo.net.bean.ChannelBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Created on 2019/4/11 13:52
 * .
 *
 * @author yj
 * @org 浙江房超信息科技有限公司
 */
interface HttpInterface {

    @GET("news/get")
    fun getNews(@QueryMap par: HashMap<String, String>): Call<BaseParser>

    @GET("news/channel")
    fun getchannel(@QueryMap par: HashMap<String, String>): Call<ChannelBean>
}