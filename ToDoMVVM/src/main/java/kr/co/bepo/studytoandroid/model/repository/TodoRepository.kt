package kr.co.bepo.studytoandroid.model.repository

import android.app.Application
import androidx.lifecycle.LiveData
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kr.co.bepo.studytoandroid.model.TodoModel
import kr.co.bepo.studytoandroid.model.db.TodoDatabase
import kr.co.bepo.studytoandroid.model.db.dao.TodoDao

class TodoRepository(application: Application) {
    private var todoDatabase: TodoDatabase
    private var todoDao: TodoDao
    private var todoItems: LiveData<List<TodoModel>>

    init {
        todoDatabase = TodoDatabase.getInstance(application)
        todoDao = todoDatabase.todoDao()
        todoItems = todoDao.getTodoList()
    }

    fun getTodoList(): LiveData<List<TodoModel>> =
        todoItems

    fun insertTodo(todoModel: TodoModel) {
        Observable.just(todoModel)
            .subscribeOn(Schedulers.io())
            .subscribe({
                todoDao.insertTodo(todoModel)

            }, {
                it.printStackTrace()
            })
    }
}