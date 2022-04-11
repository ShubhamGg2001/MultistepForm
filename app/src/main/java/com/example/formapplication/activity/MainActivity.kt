package com.example.formapplication.activity

import addDots
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.formapplication.PopUp.CustomAlert
import com.example.formapplication.R
import com.example.formapplication.adapter.RecyclerFormAdapter
import com.example.formapplication.databinding.ActivityMainBinding
import com.example.formapplication.model.FormModel
import com.example.formapplication.model.UserDetails
import com.example.formapplication.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    var currentPage = 0
    lateinit var userDetails: UserDetails
    var list = ArrayList<FormModel>()
    lateinit var adapter: RecyclerFormAdapter
    var flag: Boolean = false
    lateinit var mainViewModel: MainViewModel
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        userDetails = UserDetails()
        initializeViewPager()
        with(binding) {
            pullToRefresh.setOnRefreshListener {
                Handler().postDelayed(Runnable {
                    pullToRefresh.isRefreshing = false
                }, 2000)
            }
        }
    }

    private fun initializeViewPager() {
        with(binding) {
            list = mainViewModel.getFormModelList(this@MainActivity) as ArrayList<FormModel>
            adapter = RecyclerFormAdapter(list, userDetails)
            viewPager.isUserInputEnabled = false
            viewPager.adapter = adapter
            addDots(currentPage, list.size, llDotsIndicator)
            finish.setOnClickListener {
                    Log.d("Object", "initializeViewPager: " + userDetails.toString())
                    val customalertdialog = CustomAlert(userDetails)
                    customalertdialog.isCancelable = false
                    customalertdialog.show(supportFragmentManager, "customalert")
                    userDetails = UserDetails()
                    adapter.editUserDetails(userDetails)
                    Handler().postDelayed(Runnable {
                        viewPager.adapter = adapter
                    }, 1000)
            }
            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    if (position == (list.size - 1)) {
                        finish.visibility = View.VISIBLE
                        next.visibility = View.GONE
                    } else {
                        finish.visibility = View.GONE
                        next.visibility = View.VISIBLE
                    }
                    next.setOnClickListener {
                        Log.d("TAG", "onCreate: " + viewPager.currentItem)
                       viewPager.setCurrentItem(position + 1, false)
                    }
                    back.setOnClickListener {
                        Log.d("TAG", "onCreate: " + viewPager.currentItem)
                        viewPager.setCurrentItem(position - 1, false)
                    }
                    if (flag) {
                        currentPage = position
                        addDots(currentPage, list.size, findViewById(R.id.llDotsIndicator))
                    }
                    if (flag == false)
                        flag = true
                }
            })
        }
    }

}