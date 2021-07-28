package kr.co.bepo.studytoandroid.view

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kr.co.bepo.studytoandroid.databinding.ActivityTodoListBinding
import kr.co.bepo.studytoandroid.databinding.DialogAddTodoBinding
import kr.co.bepo.studytoandroid.model.TodoModel
import kr.co.bepo.studytoandroid.view.adapter.TodoListAdapter
import kr.co.bepo.studytoandroid.viewmodel.TodoViewModel

class TodoListActivity : AppCompatActivity() {

    private val binding by lazy { ActivityTodoListBinding.inflate(layoutInflater) }
    private val todoViewModel: TodoViewModel by lazy {
        ViewModelProvider.AndroidViewModelFactory.getInstance(
            application
        ).create(TodoViewModel::class.java)
    }
    private val todoAdapter: TodoListAdapter by lazy {
        TodoListAdapter().apply {
            listener = object : TodoListAdapter.OnTodoItemClickListener {
                override fun onTodoItemClick(position: Int) {
                    openModifyTodoDialog(getItem(position))
                }

                override fun onTodoItemLongClick(position: Int) {
                    openDeleteTodoDialog(getItem(position))
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViews()
        initViewModel()
    }

    private fun initViews() = with(binding) {
        recyclerView.adapter = todoAdapter

        addTodoButton.setOnClickListener {
            openAddTodoDialog()
        }
    }

    private fun openAddTodoDialog() {
        val dialogAddTodoBinding = DialogAddTodoBinding.inflate(layoutInflater)
        AlertDialog.Builder(this@TodoListActivity)
            .setTitle("추가하기")
            .setView(dialogAddTodoBinding.root)
            .setPositiveButton("확인") { _, _ ->
                val title = dialogAddTodoBinding.titleEditText.text.toString()
                val description = dialogAddTodoBinding.descriptionEditText.text.toString()
                val createDate = System.currentTimeMillis()

                val todoModel =
                    TodoModel(title = title, description = description, createdDate = createDate)
                todoViewModel.insertTodo(todoModel)
            }
            .setNegativeButton("취소", null)
            .create()
            .show()
    }

    private fun initViewModel() {
        todoViewModel.getTodoList().observe(this, {
            todoAdapter.setTodoItems(it)
        })
    }

    private fun openModifyTodoDialog(todoModel: TodoModel) {
        val dialogAddTodoBinding = DialogAddTodoBinding.inflate(layoutInflater)
        dialogAddTodoBinding.titleEditText.setText(todoModel.title)
        dialogAddTodoBinding.descriptionEditText.setText(todoModel.description)

        AlertDialog.Builder(this@TodoListActivity)
            .setTitle("수정하기")
            .setView(dialogAddTodoBinding.root)
            .setPositiveButton("확인") { _, _ ->
                val title = dialogAddTodoBinding.titleEditText.text.toString()
                val description = dialogAddTodoBinding.descriptionEditText.text.toString()

                todoModel.title = title
                todoModel.description = description

                todoViewModel.updateTodo(todoModel)
            }
            .setNegativeButton("취소", null)
            .create()
            .show()
    }

    private fun openDeleteTodoDialog(todoModel: TodoModel) {
        AlertDialog.Builder(this@TodoListActivity)
            .setTitle("삭제하기")
            .setMessage("확인을 누르면 삭제됩니다.")
            .setPositiveButton("확인") { _, _ ->
                todoViewModel.deleteTodo(todoModel)
            }
            .setNegativeButton("취소", null)
            .create()
            .show()
    }
}