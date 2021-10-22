package com.apps.pcomapp.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "filmsTable")
data class FilmsModel(
    val description: String,
    val director: String,
    @PrimaryKey val id: String,
    val image: String,
/*    @TypeConverters(Converters::class)
    val locations: List<String>,*/
    val movie_banner: String,
    val original_title: String,
    val original_title_romanised: String,
/*    @TypeConverters(Converters::class)
    val people: List<String>,*/
    val producer: String,
    val release_date: String,
    val rt_score: String,
    val running_time: String,
/*    @TypeConverters(Converters::class)
    val species: List<String>,*/
    val title: String,
    val url: String
/*    @TypeConverters(Converters::class)
    val vehicles: List<String>*/
)