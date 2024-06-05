package com.example.todoapp.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg todo:Todo)

//    @Query("SELECT * FROM to-do")
//    fun selectAllTodo(): List<To-do>
    @Query("SELECT * FROM todo ORDER BY priority DESC")
    fun selectAllTodo(): List<Todo>

    @Query("SELECT * FROM todo WHERE uuid= :id")
    fun selectTodo(id:Int): Todo

    @Query("UPDATE todo SET title=:title, notes=:notes, priority=:priority, is_done=:is_done WHERE uuid = :id")
    fun update(title:String, notes:String, priority:Int, is_done:Int, id:Int)
//    yang atas complete
//    yang bawah passing object dengan memanfaatkan anotasi
    @Update
    fun updateTodo(todo: Todo)

    @Query("UPDATE todo SET is_done=:is_done WHERE uuid = :id")
    fun update_is(is_done:Int, id:Int)

    @Delete
    fun deleteTodo(todo:Todo) //memasukkan object ke parameter yang mau kita delete
}
