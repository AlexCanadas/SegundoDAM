package com.example.ligaapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("all_leagues.php")
    fun getLigas(): Call<LeagueResponse>

    @GET("search_all_teams.php")
    fun getEquiposPorLiga(@Query("id") leagueId: String): Call<TeamResponse>
}