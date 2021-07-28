package kr.co.bepo.studytoandroid.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kr.co.bepo.studytoandroid.model.TodoModel
import kr.co.bepo.studytoandroid.model.repository.TodoRepository

class TodoViewModel(application: Application) : AndroidViewModel(application) {
    private val todoRepository: TodoRepository = TodoRepository(application)
    private val todoItems: LiveData<List<TodoModel>> = todoRepository.getTodoList()

    fun getTodoList(): LiveData<List<TodoModel>> =
        todoItems

    fun insertTodo(todoModel: TodoModel) {
        todoRepository.insertTodo(todoModel)
    }

    fun updateTodo(todoModel: TodoModel) {
        todoRepository.updateTodo(todoModel)
    }

    fun deleteTodo(todoModel: TodoModel) {
        todoRepository.deleteTodo(todoModel)
    }
}