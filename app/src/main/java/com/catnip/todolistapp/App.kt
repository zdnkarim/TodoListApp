package com.catnip.todolistapp

import android.app.Application
import com.catnip.todolistapp.data.datasource.TaskDataSource

class App : Application() {
    private var dataSource : TaskDataSource? = null

    override fun onCreate() {
        super.onCreate()
        dataSource = TaskDataSource()
    }

    fun getDataSource() : TaskDataSource? = dataSource
}