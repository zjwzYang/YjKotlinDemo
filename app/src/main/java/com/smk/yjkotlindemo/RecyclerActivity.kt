package com.smk.yjkotlindemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.smk.yjkotlindemo.adapter.RecyclerAdapter
import org.jetbrains.anko.find

/**
 * Created on 2019/4/12 15:07
 * .
 *
 * @author yj
 * @org 浙江房超信息科技有限公司
 */
class RecyclerActivity : AppCompatActivity() {

    private var mRecyV: RecyclerView? = null
    private var mAdapter: RecyclerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

        mRecyV = find(R.id.recycler_recy)
        mRecyV?.layoutManager = LinearLayoutManager(this)
        mAdapter = RecyclerAdapter(this, initDataForCycler())
        mRecyV?.adapter = mAdapter
    }

    fun initDataForCycler(): MutableList<String> {
        var data: MutableList<String> = mutableListOf()
        for (i in 0..100) {
            data.add("第 $i 个")
        }
        return data
    }
}