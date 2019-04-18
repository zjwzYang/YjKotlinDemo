package com.smk.yjkotlindemo.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.smk.yjkotlindemo.R
import org.jetbrains.anko.find
import org.jetbrains.anko.toast

/**
 * Created on 2019/4/11 15:26
 * .
 *
 * @author yj
 * @org 浙江房超信息科技有限公司
 */
class RecyclerAdapter(var context: Context, var dataList: MutableList<String>) :
    RecyclerView.Adapter<RecyclerAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MainViewHolder {
        var view: View = LayoutInflater.from(context).inflate(R.layout.recycler_item, p0, false)
        return MainViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.textV?.text = dataList[position]
        holder.textV?.setOnClickListener {
            context.toast(dataList[position])
        }
    }

    class MainViewHolder : RecyclerView.ViewHolder {
        var textV: TextView? = null

        constructor(itemView: View) : super(itemView) {
            textV = itemView.find(R.id.main_item_text)
        }
    }
}