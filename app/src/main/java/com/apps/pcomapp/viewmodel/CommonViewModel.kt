package com.apps.pcomapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import com.apps.pcomapp.repository.CommonRepository
import com.apps.pcomapp.room.FilmsDatabase


class CommonViewModel(application: Application) : AndroidViewModel(application) {
    val dao = FilmsDatabase.getDatabase(application).getNotesDao()
    private var commonRepository: CommonRepository? = CommonRepository(application)

    val films = commonRepository?.getFilms()?.asLiveData()
}










