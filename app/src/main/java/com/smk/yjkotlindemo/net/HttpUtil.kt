package com.smk.yjkotlindemo.net

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created on 2019/4/18 13:37
 * .
 *
 * @author yj
 * @org 浙江房超信息科技有限公司
 */
object HttpUtil {
    var retrofit: Retrofit? = null
    fun buildRetrofit(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder().baseUrl("http://api.jisuapi.com/")
                .client(getCusHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }

    private fun getCusHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
            .addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val request = chain.request()
                    val response = chain.proceed(request)
                    val builder = response.newBuilder()
                    val clone = builder.build()
                    var body = clone.body()
                    if (body != null) {
                        val mediaType = body!!.contentType()
                        if (mediaType != null) {
                            val resp = body!!.string()
                            Log.i("12345678", resp)
                            body = ResponseBody.create(mediaType, resp)
                            return response.newBuilder().body(body).build()
                        }
                    }
                    return response
                }

            })
            .connectTimeout(20, TimeUnit.SECONDS)
            .build()
        return client
    }
}