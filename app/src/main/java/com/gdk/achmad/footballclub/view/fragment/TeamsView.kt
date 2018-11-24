package com.gdk.achmad.footballclub.view.fragment

import com.gdk.achmad.footballclub.model.team.Team

interface TeamsView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)
}