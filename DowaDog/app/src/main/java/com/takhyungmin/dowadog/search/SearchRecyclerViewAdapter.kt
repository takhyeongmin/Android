package com.takhyungmin.dowadog.search

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.searchkeywordresult.SearchKeywordResultActivity
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.textColor


class SearchRecyclerViewAdapter(var ctx: Context, var recommendKeyword: ArrayList<String>) : RecyclerView.Adapter<SearchRecyclerViewAdapter.SearchRecyclerViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SearchRecyclerViewHolder {
        val searchView : View = LayoutInflater.from(p0.context).inflate(R.layout.rv_item_search_act_keyword, p0, false)
        return SearchRecyclerViewHolder(searchView)
    }

    override fun getItemCount(): Int = recommendKeyword.size

    override fun onBindViewHolder(holder: SearchRecyclerViewHolder, position: Int) {
        holder.recommendKeyword.text = recommendKeyword[position]

        holder.btnRootView.setOnTouchListener(View.OnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    holder.recommendKeyword.textColor = Color.parseColor("#FFFFFF")
                    holder.sharp.textColor = Color.parseColor("#FFFFFF")
                    holder.btnRootView.isSelected = true
                    Log.v("TAGG", holder.recommendKeyword.text.toString())
                }

                MotionEvent.ACTION_UP -> {
                    holder.recommendKeyword.textColor = Color.parseColor("#707070")
                    holder.sharp.textColor = Color.parseColor("#707070")
                    holder.btnRootView.isSelected = false
                    val it = Intent(ctx, SearchKeywordResultActivity::class.java)
                    // 서비스는 태스크가 없기 때문에 액티비티를 시작하려면 new task 플래그를 줘야 한다
                    it.addFlags(FLAG_ACTIVITY_NEW_TASK)
                    it.putExtra("keyword", holder.recommendKeyword.text.toString())
                    ctx.startActivity(it)
                    // ctx.startActivity<SearchKeywordResultActivity>("keyword" to holder.recommendKeyword.text.toString())
                }
            }
            true
        }
        )
    }

    inner class SearchRecyclerViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView!!) {
        var recommendKeyword : TextView = itemView!!.findViewById(R.id.tv_keyword_rv_item_search_act) as TextView
        var sharp : TextView = itemView!!.findViewById(R.id.tv_sharp_rv_item_search_act) as TextView
        var btnRootView : RelativeLayout = itemView!!.findViewById(R.id.rl_root_view_rv_item_search_act_keyword)

    }
}