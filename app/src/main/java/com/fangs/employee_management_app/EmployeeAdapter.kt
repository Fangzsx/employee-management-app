package com.fangs.employee_management_app

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class EmployeeAdapter(private val context : Context, private val employeeList : ArrayList<EmployeeModel>) : RecyclerView.Adapter<EmployeeAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvEmail: TextView = itemView.findViewById(R.id.tv_item_email)
        val btnEdit: ImageButton = itemView.findViewById(R.id.btn_item_edit)
        val btnDelete: ImageButton = itemView.findViewById(R.id.btn_item_delete)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_view,parent,false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //current employee
        val currentItem = employeeList[position]
        holder.tvName.text = currentItem.name
        holder.tvEmail.text = currentItem.email
        
        //edit onclick listener
        holder.btnEdit.setOnClickListener {

            if(context is MainActivity){
                context.editRecord(currentItem)
            }
        }

        holder.btnDelete.setOnClickListener {
            if(context is MainActivity){
                context.deleteRecord(currentItem)
            }
        }


    }

    override fun getItemCount(): Int = employeeList.size

}