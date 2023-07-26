package com.example.myrncapp.presentation.questionDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.example.myrncapp.R
import com.example.myrncapp.databinding.FragmentQuestionDetailsBinding

class QuestionDetailsFragment : Fragment() {

    private lateinit var binding: FragmentQuestionDetailsBinding
    private var result: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_question_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentQuestionDetailsBinding.bind(view)

        // Use the Kotlin extension in the fragment-ktx artifact.
        setFragmentResultListener("requestKey") { _, bundle ->
            // We use a String here, but any type that can be put in a Bundle is supported.
            result = bundle.getString("bundleKey")
            // Do something with the result.
            result?.let {
                binding.questionDetailsTitle.text = it
            }
        }
    }

}