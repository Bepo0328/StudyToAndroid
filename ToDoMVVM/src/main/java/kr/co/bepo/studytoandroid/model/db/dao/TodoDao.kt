package kr.co.bepo.studytoandroid.model.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kr.co.bepo.studytoandroid.model.TodoModel

@Dao
interface TodoDao {

    @Query("SELECT * FROM Todo ORDER BY createdDate ASC")
    fun getTodoList(): LiveData<List<TodoModel>>

    @Insert
    fun insertTodo(todoModel: TodoModel)
}