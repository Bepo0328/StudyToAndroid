package kr.co.bepo.studytoandroid.view.adapter

import androidx.recyclerview.widget.DiffUtil
import kr.co.bepo.studytoandroid.model.TodoModel

class TodoListDiffCallback(val oldTodoList: List<TodoModel>, val newTodoList: List<TodoModel>): DiffUtil.Callback() {
    override fun getOldListSize(): Int =
        oldTodoList.size

    override fun getNewListSize(): Int =
        newTodoList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldTodoList[oldItemPosition].id == newTodoList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldTodoList[oldItemPosition] == newTodoList[newItemPosition]
}