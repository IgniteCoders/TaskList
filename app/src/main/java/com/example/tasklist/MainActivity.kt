package com.example.tasklist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.tasklist.data.Task
import com.example.tasklist.data.TaskDAO

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val taskDAO = TaskDAO(this)

        var task = Task(-1, "Comprar leche", false)
        taskDAO.insert(task)

        Log.i("DATABASE", task.toString())

        task.done = true

        taskDAO.update(task)

        Log.i("DATABASE", task.toString())

        task = taskDAO.find(task.id)!!

        Log.i("DATABASE", task.toString())

        taskDAO.delete(task)

        val taskList = taskDAO.findAll()

        Log.i("DATABASE", taskList.toString())


        findViewById<Button>(R.id.button).setOnClickListener {
            taskDAO.findAll()
        }
    }
}