package com.github.qsubq.rampetproject.screen.episodes

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.RecyclerView
import com.github.qsubq.rampetproject.databinding.FragmentEpisodesBinding
import com.github.qsubq.rampetproject.screen.character.CharacterAdapter

class EpisodesFragment : Fragment() {
    private lateinit var binding : FragmentEpisodesBinding
    private lateinit var adapter : EpisodesAdapter
    private lateinit var recyclerView : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEpisodesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        getEpisodes()
    }

    private fun init() {
        recyclerView = binding.rcView
        adapter = EpisodesAdapter()
        recyclerView.adapter = adapter
    }

    private fun getEpisodes(){
        val viewModel = ViewModelProvider(this).get(EpisodesViewModel::class.java)

        if(viewModel.episodesList.value == null){
            if (viewModel.isOnline()){
                viewModel.getAllEpisodes()
            }
            else{

            }
        }

        viewModel.episodesList.observe(viewLifecycleOwner){ list ->
            list.body()?.let { adapter.setList(it.results) }

        }

    }
}