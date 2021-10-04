package com.catnip.todolistapp.ui.taskform

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.catnip.todolistapp.App
import com.catnip.todolistapp.R
import com.catnip.todolistapp.data.model.Task
import com.catnip.todolistapp.databinding.ActivityTaskFormBinding
import kotlin.random.Random

class TaskFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTaskFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTitleActionBar()
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.btnSaveTask.setOnClickListener {
            saveTodo()
        }
    }

    private fun saveTodo() {
        if (isFromTodoFiled()) {
            val todo = Task(
                Random.nextInt(),
                binding.etTaskName.text.toString(),
                binding.etTaskDesc.text.toString(),
                binding.etTaskHeaderImg.text.toString(),
                false
            )
            (application as App).getDataSource()?.add(todo)
            Toast.makeText(this,"Task Saved!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun isFromTodoFiled(): Boolean {
        val title = binding.etTaskName.text.toString()
        val desc = binding.etTaskDesc.text.toString()
        val imgHeaderUrl = binding.etTaskHeaderImg.text.toString()
        var isFromValid = true

        if (title.isEmpty()) {
            isFromValid = false
            binding.tilTaskName.isErrorEnabled = true
            binding.tilTaskName.error = "must be field !"
        } else {
            binding.tilTaskName.isErrorEnabled = true
        }
        if (desc.isEmpty()) {
            isFromValid = false
            binding.tilTaskDesc.isErrorEnabled = true
            binding.tilTaskDesc.error = "must be field !"
        } else {
            binding.tilTaskDesc.isErrorEnabled = true
        }
        if (imgHeaderUrl.isEmpty()) {
            isFromValid = false
            binding.tilTaskHeaderImg.isErrorEnabled = true
            binding.tilTaskHeaderImg.error = "must be field !"
        } else {
            binding.tilTaskHeaderImg.isErrorEnabled = true
        }
        return isFromValid
    }

    private fun setTitleActionBar() {
        supportActionBar?.title = getString(R.string.text_title_task_form_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}