package com.smk.yjkotlindemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.google.gson.Gson
import com.smk.yjkotlindemo.adapter.NewsAdapter
import com.smk.yjkotlindemo.net.HttpInterface
import com.smk.yjkotlindemo.net.HttpUtil
import com.smk.yjkotlindemo.net.bean.BaseParser
import com.smk.yjkotlindemo.net.bean.NewBean
import kotlinx.android.synthetic.main.activity_okhttp.*
import okhttp3.*
import org.jetbrains.anko.find
import org.jetbrains.anko.toast
import java.io.IOException

/**
 * Created on 2019/4/15 09:42
 * .
 *
 * @author yj
 * @org 浙江房超信息科技有限公司
 */
class OkHttpActivity : AppCompatActivity() {

    var okRecy: RecyclerView? = null
    var newsAdapter: NewsAdapter? = null
    var needClear: Boolean? = null
    var num: Int = 0
    var httpType: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_okhttp)

        okRecy = find(R.id.ok_recy)
        okRecy?.layoutManager = LinearLayoutManager(this)
        newsAdapter = NewsAdapter(this, mutableListOf())
        newsAdapter?.setlistener(object : NewsAdapter.OnLoadMoreListener {
            override fun onLoadMore() {
                if (httpType == 1) {
                    startOkHttp()
                } else if (httpType == 2) {
                    startRetrofit()
                }
            }
        })
        okRecy?.adapter = newsAdapter

        ok_okhttp_start.setOnClickListener {
            num = 0
            needClear = true
            httpType = 1
            startOkHttp()
        }

        ok_retrofit_start.setOnClickListener {
            num = 0
            needClear = true
            httpType = 2
            startRetrofit()
        }
    }

    private fun startOkHttp() {
        var okHttpClient = OkHttpClient()
        var request =
            Request.Builder()
                .url("http://api.jisuapi.com/news/get?channel=头条&start=${num}&num=10&appkey=695f0de3bc40b627")
                .build()
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.i("12345678", e.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                var result = response?.body()!!.string()
//                Log.i("12345678", result)
                var gson: Gson = Gson()
                var baseParser = gson.fromJson(result, BaseParser::class.javaObjectType)
//                for (news in baseParser.result.list) {
//                    Log.i("12345678", news.title)
//                }
                if (baseParser.status == "0") {
                    runOnUiThread {
                        //                        var resultData = Gson().fromJson(baseParser.result, Result::class.javaObjectType)
//                        setData(resultData.list)
                    }
                } else {
                    toast(baseParser.msg)
                }
            }
        })
    }

    private fun startRetrofit() {
        val parm = HashMap<String, String>()
        parm.put("channel", "头条")
        parm.put("start", num.toString())
        parm.put("num", "10")
        parm.put("appkey", "695f0de3bc40b627")
        var retrofit = HttpUtil.buildRetrofit()
        var httpInterface = retrofit.create(HttpInterface::class.java)
        var call = httpInterface.getNews(parm)
        call.enqueue(object : retrofit2.Callback<BaseParser> {
            override fun onFailure(call: retrofit2.Call<BaseParser>, t: Throwable) {
                Log.i("12345678", t.toString())
            }

            override fun onResponse(call: retrofit2.Call<BaseParser>, response: retrofit2.Response<BaseParser>) {
                var body: BaseParser = response.body()!!
//                for (news in body.result.list) {
//                    Log.i("12345678", news.title)
//                }
                if (body.status == "0") {
                    runOnUiThread {
                        //                        var result = Gson().fromJson(body.result, Result::class.javaObjectType)
                        setData(body.result.list)
                    }
                } else {
                    toast(body.msg)
                }
            }

        })
    }

    fun setData(dataList: MutableList<NewBean>) {
        if (needClear!!) {
            newsAdapter?.clear()
            needClear = false
        }
        num += 10
        newsAdapter?.addAll(dataList)
    }

}