package com.catnip.todolistapp.ui.taskdetail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.catnip.todolistapp.App
import com.catnip.todolistapp.R
import com.catnip.todolistapp.data.model.Task
import com.catnip.todolistapp.databinding.ActivityTaskDetailBinding
import com.catnip.todolistapp.utils.ShareUtils

class TaskDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTaskDetailBinding
    private lateinit var taskData: Task

    companion object {
        const val EXTRAS_TAG_DATA = "EXTRAS_TAG_DATA"

        @JvmStatic
        fun startActivity(context: Context?, task: Task) {
            val intent = Intent(context, TaskDetailActivity::class.java)
            intent.putExtra(EXTRAS_TAG_DATA, task)
            context?.startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        getIntentData()
        bindData()
    }

    private fun getIntentData() {
        intent.extras?.getParcelable<Task>(EXTRAS_TAG_DATA)?.let {
            taskData = it
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun bindData() {
        supportActionBar?.hide()
        binding.tvTitleTask.text = taskData.title
        binding.tvDescTask.text = taskData.desc
        Glide.with(this)
            .load(taskData.imgHeaderUrl)
            .centerCrop()
            .placeholder(R.drawable.img_placeholder)
            .into(binding.ivHeaderTask)
        setFabIcon(taskData.isTaskComplete)
        binding.fab.setOnClickListener {
            setTaskCompleteStatus()
        }
        binding.ivShare.setOnClickListener {
            ShareUtils.shareText(
                this,
                "Task Title : ${taskData.title} \n Task Desc : ${taskData.desc}"
            )
        }
    }

    private fun setTaskCompleteStatus() {
        (application as App).getDataSource()?.changeTaskStatus(taskData.id)
        taskData = taskData.apply {
            isTaskComplete = !isTaskComplete
        }
        setFabIcon(taskData.isTaskComplete)
        if (taskData.isTaskComplete) {
            Toast.makeText(this, "Success set Task to Done", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Success set Task not to Done", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setFabIcon(isTaskComplete: Boolean) {
        binding.fab.setImageResource(
            if (isTaskComplete)
                R.drawable.ic_task_done_true
            else
                R.drawable.ic_task_done_false
        )
    }
}