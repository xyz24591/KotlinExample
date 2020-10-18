package com.hc.kotlinstudyexample.todo.tasks

import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
/**
 * Created by hcw  on 2020/8/23
 * 类描述：
 * all rights reserved
 */

class TasksViewModelTest {

    @Test
    fun addNewTask_setsNewTaskEvent() {

        // Given a fresh ViewModel
        val tasksViewModel = TasksViewModel(ApplicationProvider.getApplicationContext())

        // When adding a new task
        tasksViewModel.addNewTask()

        // Then the new task event is triggered
        // TODO test LiveData


    }

}