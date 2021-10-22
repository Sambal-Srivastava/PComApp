package com.apps.pcomapp.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Room
import androidx.room.withTransaction
import com.apps.pcomapp.model.FilmsModel
import com.apps.pcomapp.retrofit.ApiClient
import com.apps.pcomapp.retrofit.ApiInterface
import com.apps.pcomapp.room.FilmDatabase
import com.apps.pcomapp.room.FilmsDao
import com.apps.pcomapp.util.networkBoundResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CommonRepository(application: Application) {
    var apiInterface: ApiInterface = ApiClient.getClient()!!.create(ApiInterface::class.java)
    val db: FilmDatabase =
        Room.databaseBuilder(application, FilmDatabase::class.java, "film_database")
            .build()

    private val restaurantDao = db.filmsDao()

    fun getFilms() = networkBoundResource(
        query = {
            restaurantDao.getAllFilms()
        },
        fetch = {
            apiInterface.getFilms()
        },
        saveFetchResult = { films ->
            db.withTransaction {
                restaurantDao.deleteAllFilms()
                restaurantDao.insertFilms(films)
            }
        }
    )
}