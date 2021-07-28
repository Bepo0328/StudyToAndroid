package kr.co.bepo.mvvmtodo.ui.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.co.bepo.mvvmtodo.data.Task
import kr.co.bepo.mvvmtodo.databinding.ItemTaskBinding

class TasksAdapter(
    private val listener: OnItemClickListener
) : ListAdapter<Task, TasksAdapter.TasksViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder =
        TasksViewHolder(ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TasksViewHolder(
        private val binding: ItemTaskBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                root.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val task = getItem(position)
                        listener.onItemClick(task)
                    }
                }

                checkBoxCompleted.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val task = getItem(position)
                        listener.onCheckBoxClick(task, checkBoxCompleted.isChecked)
                    }
                }
            }
        }

        fun bind(task: Task) = with(binding) {
            checkBoxCompleted.isChecked = task.completed
            textViewName.text = task.name
            textViewName.paint.isStrikeThruText = task.completed
            labelPriority.isVisible = task.important

        }
    }

    interface OnItemClickListener {
        fun onItemClick(task: Task)
        fun onCheckBoxClick(task: Task, isChecked: Boolean)
    }

    class DiffCallback : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean =
            oldItem == newItem
    }
}