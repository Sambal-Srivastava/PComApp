package com.apps.pcomapp.room

import androidx.room.*
import com.apps.pcomapp.model.FilmsModel
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmsDao {

    @Query("SELECT * FROM filmsTable")
    fun getAllFilms(): Flow<List<FilmsModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilms(filmsModel: List<FilmsModel>)

    @Query("DELETE FROM filmsTable")
    suspend fun deleteAllFilms()


    /*   @Query("Select * from filmsTable ORDER BY id ASC")
       fun getAllFilms(): LiveData<List<FilmsModel>>*/

    /*// below method is use to update the note.
    @Update
    suspend fun update(filmsModel: List<FilmsModel>)*/
}