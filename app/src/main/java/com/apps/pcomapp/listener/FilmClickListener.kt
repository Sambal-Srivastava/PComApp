package com.apps.pcomapp.listener

import android.view.View
import com.apps.pcomapp.model.FilmsModel

public interface FilmClickListener {
    fun onItemClick(position: Int, v: View?, adapterList: List<FilmsModel>?)
}