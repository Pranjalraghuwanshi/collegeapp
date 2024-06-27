package com.example.collegeapp

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CreateTaskDialog : DialogFragment() {

    private lateinit var titleEditText: EditText
    private lateinit var amountEditText: EditText

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.create_task_dialogue_box, null)

            titleEditText = view.findViewById(R.id.edt_task)
            amountEditText = view.findViewById(R.id.edt_amount)

            builder.setView(view)
                .setPositiveButton("Create") { dialog, id ->
                    saveTaskToFirestore()
                }
                .setNegativeButton("Cancel") { dialog, id ->
                    dialog.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }



    private fun saveTaskToFirestore() {
        val title = titleEditText.text.toString()
        val amount = amountEditText.text.toString()

        val userId = Firebase.auth.currentUser?.uid // Get current user ID



        val task = hashMapOf(
            "title" to title,
            "amount" to amount,
            "userId" to userId)

        val db = Firebase.firestore
        val taskCollection = db.collection("tasks")
        taskCollection.add(task)
            .addOnSuccessListener {
                // Task added successfully, maybe dismiss the dialog here?
            }
            .addOnFailureListener { e ->
                // Handle errors, show a Toast message perhaps
            }
    }
}