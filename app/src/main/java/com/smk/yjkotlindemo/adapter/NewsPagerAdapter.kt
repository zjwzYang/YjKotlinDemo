package com.smk.yjkotlindemo.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created on 2019/4/17 13:27
 * .
 *
 * @author yj
 * @org 浙江房超信息科技有限公司
 */
class NewsPagerAdapter : FragmentPagerAdapter {

    var fragments: MutableList<Fragment>? = null

    var titles: MutableList<String>? = null

    constructor(fm: FragmentManager, fragments: MutableList<Fragment>, titles: MutableList<String>) : super(fm) {
        this.fragments = fragments
        this.titles = titles
    }

    override fun getItem(p0: Int): Fragment {
        return fragments?.get(p0)!!
    }

    override fun getCount(): Int {
        return fragments?.size!!
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles?.get(position)
    }

}