package com.smk.yjkotlindemo.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smk.yjkotlindemo.R
import com.smk.yjkotlindemo.adapter.NewsAdapter
import com.smk.yjkotlindemo.net.HttpInterface
import com.smk.yjkotlindemo.net.HttpUtil
import com.smk.yjkotlindemo.net.bean.BaseParser
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.runOnUiThread
import org.jetbrains.anko.support.v4.toast

/**
 * Created on 2019/4/18 10:29
 * .
 *
 * @author yj
 * @org 浙江房超信息科技有限公司
 */
class NewsListFragment : Fragment() {

    var recyclerView: RecyclerView? = null
    var newsAdapter: NewsAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View = inflater.inflate(R.layout.fragment_news_list, container, false)
        recyclerView = view.find(R.id.news_list_recy)
        newsAdapter = NewsAdapter(context!!, mutableListOf())
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = newsAdapter

        initData()
        return view
    }

    fun initData() {
        var title = arguments?.getString("title")
        val parm = HashMap<String, String>()
        parm.put("channel", title!!)
        parm.put("start", "0")
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
                if (body.status == "0") {
                    runOnUiThread {
                        newsAdapter?.addAll(body.result.list)
                    }
                } else {
                    toast(body.msg)
                }
            }

        })
    }

}