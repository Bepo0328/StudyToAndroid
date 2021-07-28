package kr.co.bepo.mvvmtodo.ui.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kr.co.bepo.mvvmtodo.data.TaskDao
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val taskDao: TaskDao
) : ViewModel() {

    val searchQuery = MutableStateFlow("")

    private val tasksFlow = searchQuery.flatMapLatest {
        taskDao.getTasks(it)
    }

    val tasks = tasksFlow.asLiveData()
}