package com.example.todoapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.todoapp.model.Todo
import com.example.todoapp.model.TodoDatabase
import com.example.todoapp.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class DetailTodoViewModel(application:  Application)
    :AndroidViewModel(application), CoroutineScope {
    private val job = Job()
    val todoLD = MutableLiveData<Todo>()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun addTodo(todo: Todo) {
        launch {
            val db = buildDb(getApplication())
            db.todoDao().insertAll(todo)
        }
    }

    fun fetch(uuid:Int) {
        launch {
            val db = buildDb(getApplication())
            todoLD.postValue(db.todoDao().selectTodo(uuid))
//            YANG ATAS 2 LINE. YANG BAWAH JADI 1 LINE
//            todoLD.postValue(buildDb(getApplication()).todoDao().selectTodo(uuid))
        }
    }

    fun update(title:String, notes:String, priority:Int, is_done:Int, uuid:Int) {
        launch {
            val db = buildDb(getApplication())
            db.todoDao().update(title, notes, priority, is_done, uuid)
        }
    }
    // bisa cara yang atas atau cara yang bawah
    // cara atas memasukkan 1 1
    // cara bawah kita mengirim semua dalam 1 object
    fun update(todo:Todo){
        launch {
            buildDb(getApplication()).todoDao().updateTodo(todo)
        }
    }

}
