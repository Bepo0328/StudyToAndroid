package kr.co.bepo.mvvmtodo.ui.tasks

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.co.bepo.mvvmtodo.data.TaskDao
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val taskDao: TaskDao
) : ViewModel() {
}