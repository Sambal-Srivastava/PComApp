package com.apps.pcomapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apps.pcomapp.R
import com.apps.pcomapp.listener.FilmClickListener
import com.apps.pcomapp.model.FilmsModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.row_item_films.view.*

class FilmsAdapter(
    val filmsList: List<FilmsModel>,
    val mContext: Context,
    val lowerLimit: Int,
    val upperLimit: Int
) :
    RecyclerView.Adapter<FilmsAdapter.ViewHolder>() {


    companion object{
        var clickListener: FilmClickListener? = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_item_films, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        if (position>= lowerLimit && position<=upperLimit) {
        holder.tvTitle.text = filmsList.get(position).title
        holder.tvDescription.text = filmsList.get(position).description
        holder.tvRtScore.text = filmsList.get(position).rt_score
//        Picasso.get().load(filmsList.get(position).image).into(holder.ivImage)
//        }

        Glide
            .with(mContext)
            .load(filmsList.get(position).image)
            .placeholder(R.drawable.placeholder)
            .into(holder.ivImage);
    }

    override fun getItemCount(): Int {
        return filmsList.size
//        - lowerLimit +1
    }

    fun setOnItemClickListener(clickListener: FilmClickListener) {
        FilmsAdapter.clickListener = clickListener
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle = itemView.tvTitle
        val tvDescription = itemView.tvDescription
        val tvRtScore = itemView.tvRtScore
        val ivImage = itemView.ivImage

//        itemView.onclick

    }
}