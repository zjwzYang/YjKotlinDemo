package com.smk.yjkotlindemo.net.bean

/**
 * Created on 2019/4/15 14:39
 * .
 *
 * @author yj
 * @org 浙江房超信息科技有限公司
 */
data class BaseParser(
    var status: String,
    var msg: String,
    var result: Result
)

data class Result(
    var channel: String,
    var num: String,
    var list: MutableList<NewBean>
)

