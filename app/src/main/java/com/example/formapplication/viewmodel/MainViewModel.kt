package com.example.formapplication.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.formapplication.R
import com.example.formapplication.model.FormModel

class MainViewModel:ViewModel() {


    fun getFormModelList(context: Context): List<FormModel> {
        val list = mutableListOf<FormModel>()
        list.add(
            FormModel(
                context.getString(R.string.heading_one),
                context.getString(R.string.first_name),
                context.getString(R.string.last_name)
            )
        )
        list.add(
            FormModel(
                context.getString(R.string.heading_two),
                context.getString(R.string.phone_number),
                context.getString(R.string.address)
            )
        )
        list.add(
            FormModel(
                context.getString(R.string.heading_three),
                context.getString(R.string.company_name),
                context.getString(R.string.job_title)
            )
        )
        return list
    }
}