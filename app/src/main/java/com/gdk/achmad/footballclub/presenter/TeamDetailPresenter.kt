package com.gdk.achmad.footballclub.presenter

import com.gdk.achmad.footballclub.coroutines.CoroutineContextProvider
import com.gdk.achmad.footballclub.model.ApiRepository
import com.gdk.achmad.footballclub.model.team.TeamResponse
import com.gdk.achmad.footballclub.model.TheSportDBApi
import com.gdk.achmad.footballclub.view.TeamDetailView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class TeamDetailPresenter(private val view: TeamDetailView, private val apiRepository: ApiRepository, private val gson: Gson, private val contextPool: CoroutineContextProvider = CoroutineContextProvider()){
    fun getTeamDetail(teamId: String){
        view.showLoading()
        GlobalScope.launch(contextPool.main) {
            val data = gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getTeamDetail(teamId)).await(),
                        TeamResponse::class.java)

            view.showTeamDetail(data.teams)
            view.hideLoading()
        }
    }
}