package com.smk.yjkotlindemo

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.smk.yjkotlindemo.adapter.NewsPagerAdapter
import com.smk.yjkotlindemo.fragment.NewsListFragment
import com.smk.yjkotlindemo.net.HttpInterface
import com.smk.yjkotlindemo.net.HttpUtil
import com.smk.yjkotlindemo.net.bean.ChannelBean
import org.jetbrains.anko.find
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback

/**
 * Created on 2019/4/16 13:32
 * .
 *
 * @author yj
 * @org 浙江房超信息科技有限公司
 */
class NewsActivity : AppCompatActivity() {

    var tabs: TabLayout? = null
    var mViewPager: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        tabs = find(R.id.news_tabs)
        mViewPager = find(R.id.news_pager)
        tabs?.setupWithViewPager(mViewPager)

//        var fragments: MutableList<Fragment> = mutableListOf()
//        var titles: MutableList<String> = mutableListOf()
//        for (i in 0..3) {
//            fragments.add(NewsListFragment())
//            titles.add("第${i}页")
//        }
//        var pagerAdapter = NewsPagerAdapter(supportFragmentManager, fragments, titles)
//        mViewPager?.adapter = pagerAdapter
        dataFromNet()
    }

    fun dataFromNet() {
        val parm = HashMap<String, String>()
        parm.put("appkey", "695f0de3bc40b627")
        var retrofit = HttpUtil.buildRetrofit()
        var httpInterface = retrofit.create(HttpInterface::class.java)
        var call = httpInterface.getchannel(parm)
        call.enqueue(object : Callback<ChannelBean> {
            override fun onFailure(call: Call<ChannelBean>, t: Throwable) {
            }

            override fun onResponse(call: Call<ChannelBean>, response: retrofit2.Response<ChannelBean>) {
                var channelBean = response.body()
                if (channelBean?.status == "0") {
                    runOnUiThread {
                        var list = channelBean.result
                        var fragments: MutableList<Fragment> = mutableListOf()
                        var titles: MutableList<String> = mutableListOf()
                        for (title in list) {
                            Log.i("12345678", title)
                            var fragment = NewsListFragment()
                            var bundle = Bundle()
                            bundle.putString("title", title)
                            fragment.arguments = bundle
                            fragments.add(fragment)
                            titles.add(title)
                        }
                        var pagerAdapter = NewsPagerAdapter(supportFragmentManager, fragments, titles)
                        mViewPager?.adapter = pagerAdapter
                    }

                } else {
                    toast(channelBean!!.msg)
                }
            }

        })
    }

}