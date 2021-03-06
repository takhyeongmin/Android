package com.takhyungmin.dowadog.presenter.activity

import android.support.v4.app.Fragment
import com.takhyungmin.dowadog.home.activity.HomeActivity
import com.takhyungmin.dowadog.home.fragment.HomeFragment
import com.takhyungmin.dowadog.home.model.HomeModel
import com.takhyungmin.dowadog.home.model.get.GetDuplicateResponse
import com.takhyungmin.dowadog.home.model.get.GetUserInfoData
import com.takhyungmin.dowadog.presenter.BasePresenter

class HomeActivityPresenter : BasePresenter<HomeActivity>() {

    private val homeModel : HomeModel by lazy {
        HomeModel()
    }

    fun initView(){
        view!!.addFragment(HomeFragment())
    }

    fun replaceFragment(fragment : Fragment){
        view!!.replaceFragment(fragment)
    }

    fun adjustDim(percent : Float){
        view!!.adjustDim(percent)
    }

    fun requestData(id : String){
        homeModel.getDuplicateData(id)
    }

    fun responseData(data : GetDuplicateResponse){
        view!!.responseData(data)
    }

    fun toNew(){
        view!!.toNew()
    }

    fun homeBtnClick(){
        view!!.clickedAdoptBtn()
    }

    fun requestUserInfo(){
        homeModel.getUserInfo()
    }

    fun responseUserInfo(userData : GetUserInfoData){
        view!!.responseUserInfo(userData)
    }
}