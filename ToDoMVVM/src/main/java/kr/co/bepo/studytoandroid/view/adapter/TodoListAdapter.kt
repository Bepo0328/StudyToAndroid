package kr.co.bepo.studytoandroid.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.bepo.studytoandroid.databinding.ViewholderTodoListItemBinding
import kr.co.bepo.studytoandroid.model.TodoModel
import java.text.SimpleDateFormat
import java.util.*

class TodoListAdapter : RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() {

    private val todoItems: ArrayList<TodoModel> = arrayListOf()

    inner class TodoViewHolder(
        private val binding: ViewholderTodoListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(todoModel: TodoModel) = with(binding) {
            titleTextView.text = todoModel.title
            descriptionTextView.text = todoModel.description
            createdDateTextView.text =
                SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA).format(todoModel.createdDate)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder =
        TodoViewHolder(
            ViewholderTodoListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(todoItems[position])
    }

    override fun getItemCount(): Int =
        todoItems.size

    fun addItem(todoModel: TodoModel) {
        todoItems.add(todoModel)
    }
}