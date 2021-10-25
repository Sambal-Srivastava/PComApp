package com.apps.pcomapp.views

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.apps.pcomapp.R
import com.apps.pcomapp.adapter.FilmsAdapter
import com.apps.pcomapp.listener.FilmClickListener
import com.apps.pcomapp.model.FilmsModel
import com.apps.pcomapp.util.Helper
import com.apps.pcomapp.util.MyPreferences
import com.apps.pcomapp.viewmodel.CommonViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(R.layout.fragment_home) {
    var filmsList: MutableList<FilmsModel> = mutableListOf<FilmsModel>()
    var resultList: MutableList<FilmsModel> = mutableListOf<FilmsModel>()
    lateinit var mContext: Context
    lateinit var filmsAdapter: FilmsAdapter
    private var commonViewModel: CommonViewModel? = null
    private var startPoint = 0
    private var endPoint = 10
//    val myAdapterList: List<FilmsModel>

    //===============pagination================
    var isScrolling = false
    var currentItems = 0
    var totalItems: Int = 0
    var scrollOutItems: Int = 0
    var manager: LinearLayoutManager? = null


    private var lowerLimit = 0
    private var upperLimit = 10

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mContext = requireContext()
        //===============================
        commonViewModel = ViewModelProvider(this).get(CommonViewModel::class.java)
        requireActivity().btNav.visibility = View.VISIBLE

        manager = LinearLayoutManager(
            mContext, LinearLayoutManager.VERTICAL, false
        )
        rvFilms?.layoutManager = manager

        commonViewModel!!.films?.observe(viewLifecycleOwner) { result ->
            resultList = result.data as MutableList<FilmsModel>
            pbLoading.visibility = View.VISIBLE
            if (!resultList.isEmpty()) {
                startPagination(resultList)
            }
        }

        // adding on scroll change listener method for our nested scroll view.

        // adding on scroll change listener method for our nested scroll view.
        nestedSV.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            // on scroll change we are checking when users scroll as bottom.
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                // in this method we are incrementing page number,
                pbLoading.visibility = View.VISIBLE
                startPagination(resultList)
            }
        })
    }

    private fun startPagination(data: List<FilmsModel>?) {
        for (a in startPoint..endPoint) {
            if (a == endPoint) {
                startPoint = endPoint
                if (endPoint + 10 > data!!.size) {
                    endPoint = endPoint + (data.size - endPoint)
                    tvLoadMore.visibility = View.GONE
                } else {
                    endPoint = endPoint + 10
                }
                break
            }
            filmsList.add(data!!.get(a))
        }
        setMyAdapter(filmsList)
        pbLoading.visibility = View.GONE
    }

    private fun setMyAdapter(it: List<FilmsModel>?) {
        filmsAdapter = FilmsAdapter(it!!, mContext, lowerLimit, upperLimit)
        rvFilms.setAdapter(filmsAdapter)
        filmsAdapter.setOnItemClickListener(object : FilmClickListener {
            override fun onItemClick(position: Int, v: View?, adapList: List<FilmsModel>?) {
                val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(
                    adapList?.get(position)!!.title,
                    adapList.get(position).original_title,
                    adapList.get(position).image,
                    adapList.get(position).description,
                    adapList.get(position).director,
                    adapList.get(position).producer,
                    adapList.get(position).release_date,
                    adapList.get(position).running_time,
                    adapList.get(position).rt_score,
                )
                findNavController().navigate(action)
                activity!!.btNav.visibility = View.GONE
            }
        })


    }
}