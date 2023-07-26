package com.example.myrncapp.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myrncapp.R
import com.example.myrncapp.databinding.FragmentHomeBinding
import com.example.myrncapp.networking.ApiClient
import com.example.myrncapp.networking.ApiInterface
import com.example.myrncapp.networking.model.Question
import com.example.myrncapp.networking.model.QuestionList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    val TAG = "HomeFragment"
    private var mAdapter: ListAdapter? = null;
    private var mQuestions: MutableList<Question> = ArrayList()
    private lateinit var binding : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)
        binding.questionsRecyclerview.layoutManager = LinearLayoutManager(requireContext())

        mAdapter = ListAdapter(requireContext(), mQuestions)
        binding.questionsRecyclerview.adapter = mAdapter

        mAdapter!!.setOnClickListener(object :
            ListAdapter.OnClickListener {
            override fun onClick(position: Int, question: Question) {
                Log.i(TAG, "Clicked on question with title  " + question.title)
                val result = question.title
                // Use the Kotlin extension in the fragment-ktx artifact.
                setFragmentResult("requestKey", bundleOf("bundleKey" to result))
                findNavController().navigate(R.id.action_homeFragment_to_questionDetailsFragment)
            }
        })

        fetchQuestionsList()
    }

    private fun fetchQuestionsList() {
        val mApiService: ApiInterface = ApiClient.client.create(ApiInterface::class.java)

        val call = mApiService.fetchQuestions("android");
        call.enqueue(object : Callback<QuestionList> {

            override fun onResponse(call: Call<QuestionList>, response: Response<QuestionList>) {

                Log.i(TAG, "onResponse: success , with list size " + response.body()?.items?.size)
                val questions = response.body()
                if (questions != null) {
                    mQuestions.addAll(questions.items!!)
                    mAdapter!!.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<QuestionList>, t: Throwable) {
                Log.e(TAG, "onFailure: " + t.message)
            }
        })
    }

}