package kr.co.bepo.mvvmtodo.ui.tasks

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.co.bepo.mvvmtodo.R

@AndroidEntryPoint
class TasksFragment : Fragment(R.layout.fragment_tasks) {

    private val viewModel: TasksViewModel by viewModels()
}