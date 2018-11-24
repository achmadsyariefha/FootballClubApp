package com.gdk.achmad.footballclub.view

import com.gdk.achmad.footballclub.model.team.Team

interface TeamDetailView{
    fun showLoading()
    fun hideLoading()
    fun showTeamDetail(data: List<Team>)
}