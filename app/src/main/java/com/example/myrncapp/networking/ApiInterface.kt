package com.example.myrncapp.networking

import com.example.myrncapp.networking.model.QuestionList
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("/2.2/questions?order=desc&sort=creation&site=stackoverflow")
    fun fetchQuestions(@Query("tagged") tags: String): Call<QuestionList>

}