package com.example.collegeapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Homepagefragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.homepagefragment, container, false)
        recyclerView = view.findViewById(R.id.rv_task_list)
        taskAdapter = TaskAdapter()
        recyclerView.adapter = taskAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchTasksFromFirestore()
    }

    private fun fetchTasksFromFirestore() {
        val userId = Firebase.auth.currentUser?.uid // Get current user ID
        if (userId != null) {
            val db = Firebase.firestore
            val tasksCollection = db.collection("tasks")
                .whereEqualTo("userId", userId) // Use "userId" (lowercase) to match your Firestore field

            tasksCollection.addSnapshotListener { snapshot, e ->
                if (e != null) {
                    // Handle errors
                    return@addSnapshotListener
                }

                val taskList = mutableListOf<Task>()
                if (snapshot != null) {
                    for (document in snapshot) {
                        val task = document.toObject(Task::class.java)
                        taskList.add(task)
                    }
                }
                taskAdapter.updateTasks(taskList)
            }
        } else {
            // Handle the case where the user is not authenticated
            // You might want to show a message or redirect to the login screen
        }
    }
}