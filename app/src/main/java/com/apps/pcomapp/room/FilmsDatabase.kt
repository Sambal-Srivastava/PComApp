package com.apps.pcomapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.apps.pcomapp.model.FilmsModel

@Database(entities = arrayOf(FilmsModel::class), version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class FilmsDatabase : RoomDatabase() {

    abstract fun getNotesDao(): FilmsDao

    companion object {
        // Singleton prevents multiple
        // instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: FilmsDatabase? = null

        fun getDatabase(context: Context): FilmsDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
          /*  return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FilmsDatabase::class.java,
                    "films_database"
                )
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }*/

            if(INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext, FilmsDatabase::class.java,
                    "films_database.db"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build()
            }

                return INSTANCE!!

        }

        private val roomCallback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
            }
        }
    }
}