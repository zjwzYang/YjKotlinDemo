package com.smk.yjkotlindemo.net.bean

/**
 * Created on 2019/4/16 13:45
 * .
 *
 * @author yj
 * @org 浙江房超信息科技有限公司
 */
data class NewsInput(
    var channel: String,
    var start: Int,
    var num: Int,
    var appkey: String
)