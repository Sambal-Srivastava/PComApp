package com.apps.pcomapp.retrofit

import com.apps.pcomapp.model.FilmsModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("films")
    suspend fun getFilms(
    ): List<FilmsModel>
//            Response<List<FilmsModel>>
}