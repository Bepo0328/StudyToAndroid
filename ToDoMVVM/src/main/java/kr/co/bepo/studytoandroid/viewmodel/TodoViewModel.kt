package kr.co.bepo.studytoandroid.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kr.co.bepo.studytoandroid.model.TodoModel
import kr.co.bepo.studytoandroid.model.repository.TodoRepository

class TodoViewModel(application: Application) : AndroidViewModel(application) {
    private val todoRepository: TodoRepository
    private val todoItems: LiveData<List<TodoModel>>

    init {
        todoRepository = TodoRepository(application)
        todoItems = todoRepository.getTodoList()
    }

    fun getTodoList(): LiveData<List<TodoModel>> =
        todoItems

    fun insertTodo(todoModel: TodoModel) {
        todoRepository.insertTodo(todoModel)
    }
}