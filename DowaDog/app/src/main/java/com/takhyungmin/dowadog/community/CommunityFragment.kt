package com.takhyungmin.dowadog.community

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.jakewharton.rxbinding2.view.clicks
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.community.adapter.CommunityAdapter
import com.takhyungmin.dowadog.community.model.CommunityItem
import com.takhyungmin.dowadog.communitydetail.CommunityDetailActivity
import com.takhyungmin.dowadog.communitywrite.CommunityWriteActivity
import com.takhyungmin.dowadog.presenter.fragment.CommunityFragmentPresenter
import kotlinx.android.synthetic.main.fragment_community.*

class CommunityFragment : Fragment() {

    lateinit var communityFragmentPresenter : CommunityFragmentPresenter
    lateinit var requestManager: RequestManager
    lateinit var communityAdapter : CommunityAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_community, container, false)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        communityFragmentPresenter = CommunityFragmentPresenter()
        communityFragmentPresenter.view = this
        requestManager = Glide.with(this)
    }

    override fun onStart() {
        super.onStart()
        communityFragmentPresenter.initView()
        setOnBinding()
    }

    fun initView(communityItems : ArrayList<CommunityItem>){
        communityAdapter = CommunityAdapter(communityItems, requestManager, communityFragmentPresenter, context!!)
        //contentsEduRvAdapter.setOnItemClickListener(this)
        rv_community_feeds.layoutManager = LinearLayoutManager(activity)
        rv_community_feeds.adapter = communityAdapter
    }

    fun toDetail(){
        startActivity(Intent(activity, CommunityDetailActivity::class.java))
    }

    fun setOnBinding(){
        btn_community_write.clicks().subscribe {
            startActivity(Intent(activity, CommunityWriteActivity::class.java))
        }
    }

}