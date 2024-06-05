package com.example.todoapp.view

import android.view.View
import android.widget.CompoundButton
import com.example.todoapp.model.Todo

interface TodoCheckedChangeListener {
    fun onCheckedChanged(cb: CompoundButton,
                         isChecked:Boolean,
                         todo:Todo
    ) //yang pasti akan mengirimkan compoundbutton dan boolean
    //karena parameter bawaannya gitu
}
interface TodoEditClickListener {
    fun onTodoEditClick(v: View)
}

interface RadioClickListener {
    fun onRadioClick(v:View)
}
