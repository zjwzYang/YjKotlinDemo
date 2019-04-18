package com.smk.yjkotlindemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_recy_text.setOnClickListener {
            startActivity(intentFor<RecyclerActivity>())
        }
        main_okhttp.setOnClickListener {
            startActivity<OkHttpActivity>()
        }
        main_news.setOnClickListener {
            startActivity<NewsActivity>()
        }
    }
}
