package kr.co.bepo.studytoandroid.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kr.co.bepo.studytoandroid.databinding.ViewholderTodoListItemBinding
import kr.co.bepo.studytoandroid.model.TodoModel
import java.text.SimpleDateFormat
import java.util.*

class TodoListAdapter : RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() {

    private var todoItems: List<TodoModel> = listOf()

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

    fun setTodoItems(todoItems: List<TodoModel>) {
        Observable.just(todoItems)
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(Schedulers.io())
            .map { DiffUtil.calculateDiff(TodoListDiffCallback(this.todoItems, todoItems)) }
            .subscribe({
                this.todoItems = todoItems
                it.dispatchUpdatesTo(this)
            }, {
                it.printStackTrace()
            })
    }
}