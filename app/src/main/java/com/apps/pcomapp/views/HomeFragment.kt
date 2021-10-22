package com.apps.pcomapp.views

import android.content.Context
import android.opengl.Visibility
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.apps.pcomapp.R
import com.apps.pcomapp.adapter.FilmsAdapter
import com.apps.pcomapp.model.FilmsModel
import com.apps.pcomapp.viewmodel.CommonViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.concurrent.schedule
import java.util.*


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
        mContext = requireActivity()
        //===============================
        commonViewModel = ViewModelProvider(this).get(CommonViewModel::class.java)
        manager = LinearLayoutManager(
            mContext, LinearLayoutManager.VERTICAL, false
        )
        rvFilms?.layoutManager = manager

        commonViewModel!!.films?.observe(viewLifecycleOwner) { result ->
            resultList = result.data as MutableList<FilmsModel>
           // setMyAdapter(resultList)
            if (!resultList.isEmpty()) {
                //tvLoadMore.visibility = View.VISIBLE
            }
            pbLoading.visibility = View.VISIBLE
            startPagination(resultList)
        }

        // adding on scroll change listener method for our nested scroll view.

        // adding on scroll change listener method for our nested scroll view.
        nestedSV.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            // on scroll change we are checking when users scroll as bottom.
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                // in this method we are incrementing page number,
                // making progress bar visible and calling get data method.
//                GlobalScope.launch {
//                    delay(2000)
//                }
                pbLoading.visibility = View.VISIBLE
                startPagination(resultList)
//                getDataFromAPI(page, limit)
            }
        })

    /*    tvLoadMore.setOnClickListener(View.OnClickListener {
            startPagination(resultList)
        })*/
    }

    private fun startPagination(data: List<FilmsModel>?) {
        for (a in startPoint..endPoint) {
            if (a == endPoint) {
                startPoint =  endPoint
                if (endPoint+10 > data!!.size){
                    endPoint = endPoint + (data.size - endPoint)
                    tvLoadMore.visibility = View.GONE
                }else{
                    endPoint = endPoint + 10
                }
                break
            }
            filmsList.add(data!!.get(a))
        }
        setMyAdapter(filmsList)
         pbLoading.visibility = View.GONE
    }

    private fun enablePagination(it: List<FilmsModel>?) {

        setMyAdapter(it)

        // RecyclerView Pagination********************************
/*
        rvFilms.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                currentItems = manager!!.getChildCount()
                totalItems = manager!!.getItemCount()
                scrollOutItems = manager!!.findFirstVisibleItemPosition()
                if (isScrolling && currentItems + scrollOutItems === totalItems) {
                    isScrolling = false
                    if (upperLimit != totalItems) {
                        lowerLimit = lowerLimit + 10
                        upperLimit = upperLimit + 10
                        setMyAdapter(it)
                    }
                }
            }
        })
*/
    }

    private fun setMyAdapter(it: List<FilmsModel>?) {
        filmsAdapter = FilmsAdapter(it!!, mContext, lowerLimit, upperLimit)
        rvFilms.setAdapter(filmsAdapter)
    }
}