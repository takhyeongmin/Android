package com.takhyungmin.dowadog.search

import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import com.takhyungmin.dowadog.BaseActivity
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.searchkeywordresult.SearchKeywordResultActivity
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.sdk25.coroutines.textChangedListener
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class SearchActivity : BaseActivity(), View.OnClickListener {



    override fun onClick(v: View?) {
        when (v!!) {
            // 검색 버튼
            btn_search_search_act -> {
                if(et_keyword_search_act.text.length <= 0){
                    toast("키워드를 입력해주세요")
                }else {
                    startActivity<SearchKeywordResultActivity>("keyword" to et_keyword_search_act.text.toString())
                }
            }
            btn_back_search_act -> {
                finish()
            }
            et_keyword_search_act -> {
                rl_recommend_box_search_act.visibility = View.GONE
                rl_past_keyword_box_search_act.visibility = View.VISIBLE
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)





        init()

        setSearchBtnTextChangeListener()

        var recommendKeyword: ArrayList<String> = ArrayList()
        recommendKeyword.add("유기견")
        recommendKeyword.add("유기견입양")
        recommendKeyword.add("서울")
        setFirstRVAdapter(recommendKeyword)
        var recommendKeyword2: ArrayList<String> = ArrayList()
        recommendKeyword2.add("유기견입양")
        recommendKeyword2.add("코카니")
        recommendKeyword2.add("서울지역")

        setSecondRVAdapter(recommendKeyword2)
        var recommendKeyword3: ArrayList<String> = ArrayList()
        recommendKeyword3.add("서울지역")
        recommendKeyword3.add("코카니")
        recommendKeyword3.add("유기견입양")
        setThirdRVAdapter(recommendKeyword3)

        var pastKeyword: ArrayList<String> = ArrayList()
        pastKeyword.add("검색어")
        pastKeyword.add("추천")
        pastKeyword.add("키워드들")
        pastKeyword.add("검색어")
        pastKeyword.add("추천")
        pastKeyword.add("키워드들")
        setPastSearchKeywordRVAdapter(pastKeyword)
    }

    private fun init() {
        btn_search_search_act.setOnClickListener(this)
        btn_back_search_act.setOnClickListener(this)
        et_keyword_search_act.setOnClickListener(this)
    }


    fun setSearchBtnTextChangeListener() {
        et_keyword_search_act.textChangedListener {
            afterTextChanged {
                if (et_keyword_search_act.text.toString().length > 0) {
                    btn_search_search_act.setBackgroundColor(Color.parseColor("#40D39F"))
                } else {
                    btn_search_search_act.setBackgroundColor(Color.parseColor("#C5C5C5"))
                }
            }
        }
        //et_keyword_search_act.setOn
    }

    fun setFirstRVAdapter(recommendKeyword: ArrayList<String>) {
        var recyclerViewAdapter = SearchRecyclerViewAdapter(applicationContext, recommendKeyword)
        rv_one_recommend_search_act.adapter = recyclerViewAdapter
        rv_one_recommend_search_act.layoutManager = LinearLayoutManager(applicationContext, LinearLayout.HORIZONTAL, false)
    }

    fun setSecondRVAdapter(recommendKeyword: ArrayList<String>){
        var recyclerViewAdapter = SearchRecyclerViewAdapter(applicationContext, recommendKeyword)
        rv_two_recommend_search_act.adapter = recyclerViewAdapter
        rv_two_recommend_search_act.layoutManager = LinearLayoutManager(applicationContext, LinearLayout.HORIZONTAL, false)
    }

    fun setThirdRVAdapter(recommendKeyword: ArrayList<String>){
        var recyclerViewAdapter = SearchRecyclerViewAdapter(applicationContext, recommendKeyword)
        rv_three_recommend_search_act.adapter = recyclerViewAdapter
        rv_three_recommend_search_act.layoutManager = LinearLayoutManager(applicationContext, LinearLayout.HORIZONTAL, false)
    }

    fun setPastSearchKeywordRVAdapter(pastKeyword: ArrayList<String>){
        var searchPastKeywordRVAdapter = SearchPastKeywordRVAdapter(applicationContext, pastKeyword)
        rv_past_keyword_search_act.adapter = searchPastKeywordRVAdapter
        rv_past_keyword_search_act.layoutManager = LinearLayoutManager(applicationContext)
    }

}

