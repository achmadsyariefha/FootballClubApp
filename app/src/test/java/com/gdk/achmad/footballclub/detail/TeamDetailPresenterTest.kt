package com.gdk.achmad.footballclub.detail

import com.gdk.achmad.footballclub.TestContextProvider
import com.gdk.achmad.footballclub.model.ApiRepository
import com.gdk.achmad.footballclub.model.TheSportDBApi
import com.gdk.achmad.footballclub.model.team.Team
import com.gdk.achmad.footballclub.model.team.TeamResponse
import com.gdk.achmad.footballclub.presenter.TeamDetailPresenter
import com.gdk.achmad.footballclub.view.TeamDetailView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TeamDetailPresenterTest {
    @Mock
    private
    lateinit var view: TeamDetailView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: TeamDetailPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = TeamDetailPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetTeamDetail()  {
        val teams: MutableList<Team> = mutableListOf()
        val response = TeamResponse(teams)
        val id = "1234"

        GlobalScope.launch {
            Mockito.`when`(gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getTeamDetail(id)).await(),
                    TeamResponse::class.java
            )).thenReturn(response)

            presenter.getTeamDetail(id)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showTeamDetail(teams)
            Mockito.verify(view).hideLoading()
        }
    }
}
