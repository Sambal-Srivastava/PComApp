package com.apps.pcomapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.apps.pcomapp.model.FilmsModel

@Database(entities = [FilmsModel::class], version = 1)
abstract class FilmDatabase : RoomDatabase() {

    abstract fun filmsDao(): FilmsDao
}