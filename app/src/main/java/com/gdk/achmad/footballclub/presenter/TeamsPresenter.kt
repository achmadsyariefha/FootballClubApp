package com.gdk.achmad.footballclub.presenter

import com.gdk.achmad.footballclub.coroutines.CoroutineContextProvider
import com.gdk.achmad.footballclub.view.fragment.TeamsView
import com.gdk.achmad.footballclub.model.ApiRepository
import com.gdk.achmad.footballclub.model.team.TeamResponse
import com.gdk.achmad.footballclub.model.TheSportDBApi
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamsPresenter(private val view: TeamsView, private val apiRepository: ApiRepository, private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()){
    fun getTeamList(league: String?){
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getTeams(league)).await(),
                        TeamResponse::class.java)

            view.hideLoading()
            view.showTeamList(data.teams)
        }
    }
}