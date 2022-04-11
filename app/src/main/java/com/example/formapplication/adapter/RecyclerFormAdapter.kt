package com.example.formapplication.adapter

import android.content.Context
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.formapplication.constants.Constants
import com.example.formapplication.databinding.ItemFormBinding
import com.example.formapplication.model.FormModel
import com.example.formapplication.model.UserDetails


class RecyclerFormAdapter(
    private var list: ArrayList<FormModel>,
    private var userDetails: UserDetails
) : RecyclerView.Adapter<RecyclerFormAdapter.CustomViewHolder>() {
    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val itemRowBinding: ItemFormBinding =
            ItemFormBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
        return CustomViewHolder(itemRowBinding)
    }

    fun editUserDetails(changedUserDetails: UserDetails) {
        userDetails = changedUserDetails
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.item.formModel = list.get(position)
        val fixedPostion = position
        if (position == 1) {
            holder.item.inputFirst.inputType = InputType.TYPE_CLASS_NUMBER

        }

        holder.item.inputFirst.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (fixedPostion == Constants.POSITION_ZERO) {
                    userDetails.firstName = holder.item.inputFirst.text.toString()
                } else if (fixedPostion == Constants.POSITION_ONE) {
                    userDetails.phoneNumber = holder.item.inputFirst.text.toString()
                } else if (fixedPostion == Constants.POSITION_TWO) {
                    userDetails.company_name = holder.item.inputFirst.text.toString()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                Log.d("TAG", "afterTextChanged: " + holder.item.inputFirst.text.toString())
            }

        })

        holder.item.inputSecond.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (fixedPostion == Constants.POSITION_ZERO) {
                    userDetails.lastName = holder.item.inputSecond.text.toString()
                } else if (fixedPostion == Constants.POSITION_ONE) {
                    val email = holder.item.inputSecond.text.toString()
                    if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        Toast.makeText(context, "Email Verified !", Toast.LENGTH_SHORT).show();
                    } else {
                        holder.item.inputSecond.setError("Invalid Email")
                    }
                    userDetails.address = holder.item.inputSecond.text.toString()
                } else if (fixedPostion == Constants.POSITION_TWO) {
                    userDetails.jobTitle = holder.item.inputSecond.text.toString()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })


    }

    class CustomViewHolder(var item: ItemFormBinding) : RecyclerView.ViewHolder(item.root) {

    }

    override fun getItemCount(): Int {
        return list.size
    }
}