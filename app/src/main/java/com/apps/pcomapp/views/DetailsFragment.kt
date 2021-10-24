package com.apps.pcomapp.views

import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.apps.pcomapp.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_movie_details.*

class DetailsFragment : Fragment(R.layout.fragment_movie_details) {
    private val args: DetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide
            .with(this)
            .load(args.image)
            .placeholder(R.drawable.placeholder)
            .into(ivImage)

        tvTitle.text = Html.fromHtml("<b> Title: </b>" + args.title)
        tvOriginalTitle.text = Html.fromHtml("<b> Original Title: </b>" + args.originalTitle)
        tvDescription.text = Html.fromHtml("<b> Description: </b>" + args.description)
        tvDirector.text = Html.fromHtml("<b> Director: </b>" + args.director)
        tvProducer.text = Html.fromHtml("<b> Producer: </b>" + args.producer)
        tvReleaseDate.text = Html.fromHtml("<b> Release Date: </b>" + args.releaseDate)
        tvRunningTime.text = Html.fromHtml("<b> Running Time: </b>" + args.runningTime)
        tvRtScore.text = Html.fromHtml("<b> Rt Score: </b>" + args.rtScore)
    }


}