package com.gdk.achmad.footballclub.team

import com.gdk.achmad.footballclub.TestContextProvider
import com.gdk.achmad.footballclub.model.ApiRepository
import com.gdk.achmad.footballclub.model.TheSportDBApi
import com.gdk.achmad.footballclub.model.team.Team
import com.gdk.achmad.footballclub.model.team.TeamResponse
import com.gdk.achmad.footballclub.presenter.TeamsPresenter
import com.gdk.achmad.footballclub.view.fragment.TeamsView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TeamsPresenterTest {
    @Test
    fun testGetTeamList(){
        val teams: MutableList<Team> = mutableListOf()
        val response = TeamResponse(teams)
        val league = "English Premiere League"

        GlobalScope.launch {
            Mockito.`when`(gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getTeams(league)).await(),
                    TeamResponse::class.java
            )).thenReturn(response)

            teamsPresenter.getTeamList(league)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showTeamList(teams)
            Mockito.verify(view).hideLoading()
        }
    }

    @Mock
    private
    lateinit var view: TeamsView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var teamsPresenter: TeamsPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        teamsPresenter = TeamsPresenter(view, apiRepository, gson, TestContextProvider())
    }
}