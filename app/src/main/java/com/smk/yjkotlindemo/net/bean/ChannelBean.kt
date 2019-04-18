package com.smk.yjkotlindemo.net.bean

/**
 * Created on 2019/4/18 10:57
 * .
 *
 * @author yj
 * @org 浙江房超信息科技有限公司
 */
data class ChannelBean(
    var status: String,
    var msg: String,
    var result: MutableList<String>
)