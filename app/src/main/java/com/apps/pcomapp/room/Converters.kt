package com.apps.pcomapp.room

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import com.apps.pcomapp.model.FilmsModel
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import java.io.ByteArrayOutputStream


public class Converters {
  /*  @TypeConverter
    fun listToJsonString(value: List<FilmsModel>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToList(value: String) = Gson().fromJson(value, Array<FilmsModel>::class.java).toList()*/
  @TypeConverter
  fun fromBitmap(bmp: Bitmap): ByteArray {
      val outputStream = ByteArrayOutputStream()
      bmp.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
      return outputStream.toByteArray()
  }

    @TypeConverter
    fun toBitmap(byteArray: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }


}