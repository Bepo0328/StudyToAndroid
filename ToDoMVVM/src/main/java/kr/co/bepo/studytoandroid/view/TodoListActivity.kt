package kr.co.bepo.studytoandroid.view

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kr.co.bepo.studytoandroid.databinding.ActivityTodoListBinding
import kr.co.bepo.studytoandroid.databinding.DialogAddTodoBinding
import kr.co.bepo.studytoandroid.model.TodoModel
import kr.co.bepo.studytoandroid.view.adapter.TodoListAdapter

class TodoListActivity: AppCompatActivity() {

    private val binding by lazy { ActivityTodoListBinding.inflate(layoutInflater) }
    private lateinit var adapter: TodoListAdapter
    private val dialogAddTodoBinding by lazy { DialogAddTodoBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() = with(binding) {
        adapter = TodoListAdapter()
        recyclerView.adapter = adapter

        addTodoButton.setOnClickListener {
            openAddTodoDialog()
        }
    }

    private fun openAddTodoDialog() = with(dialogAddTodoBinding) {
        AlertDialog.Builder(this@TodoListActivity)
            .setTitle("추가하기")
            .setView(root)
            .setPositiveButton("확인") { _, _ ->
                val title = titleEditText.text.toString()
                val description = descriptionEditText.text.toString()
                val createDate = System.currentTimeMillis()
                val todoModel = TodoModel(title, description, createDate)
                adapter.addItem(todoModel)
                adapter.notifyDataSetChanged()
            }
            .setNegativeButton("취소", null)
            .create()
            .show()
    }
}