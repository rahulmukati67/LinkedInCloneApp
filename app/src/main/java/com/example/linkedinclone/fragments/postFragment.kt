package com.example.linkedinclone.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.linkedinclone.R

class postFragment : Fragment() {
       lateinit var EditText:EditText
       lateinit var Post: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_post, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        EditText= view.findViewById(R.id.postText)
        Post = requireView().findViewById(R.id.post)
        EditText.setText("")
        Post.setOnClickListener() {
            val sharedPref = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            val textToSave = EditText.text.toString()
            editor.putString("savedText", textToSave)
            editor.apply()
            Toast.makeText(requireContext(), "Post Succesfully", Toast.LENGTH_SHORT).show()
        }
    }
}