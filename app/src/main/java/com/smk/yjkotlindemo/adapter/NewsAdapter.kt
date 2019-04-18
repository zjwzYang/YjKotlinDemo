package com.smk.yjkotlindemo.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.smk.yjkotlindemo.R
import com.smk.yjkotlindemo.WebActivity
import com.smk.yjkotlindemo.net.bean.NewBean
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

/**
 * Created on 2019/4/15 15:18
 * .
 *
 * @author yj
 * @org 浙江房超信息科技有限公司
 */
class NewsAdapter(private var context: Context, private var dataList: MutableList<NewBean>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    var onLoadMoreListener: OnLoadMoreListener? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): NewsViewHolder {
        var view: View = LayoutInflater.from(context).inflate(R.layout.news_item, p0, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, p1: Int) {
        var newBean = dataList[p1]
        holder.title?.text = newBean.title
        holder.time?.text = newBean.time
        Glide.with(context)
            .load(newBean.pic)
            .into(holder.picV!!)

        if (p1 == dataList.size - 2) {
            onLoadMoreListener?.onLoadMore()
        }

        holder.itemView.setOnClickListener {
            context.startActivity<WebActivity>("url" to newBean.weburl)
        }
    }

    fun clear() {
        this.dataList.clear()
        notifyDataSetChanged()
    }

    fun addAll(dataList: MutableList<NewBean>) {
        this.dataList?.addAll(dataList)
        notifyDataSetChanged()
    }

    class NewsViewHolder : RecyclerView.ViewHolder {
        var picV: ImageView? = null
        var title: TextView? = null
        var time: TextView? = null

        constructor(view: View) : super(view) {
            picV = view.find(R.id.news_pic)
            title = view.find(R.id.news_title)
            time = view.find(R.id.news_time)
        }
    }

    fun setlistener(listener: OnLoadMoreListener) {
        this.onLoadMoreListener = listener
    }

    interface OnLoadMoreListener {
        fun onLoadMore()
    }
}