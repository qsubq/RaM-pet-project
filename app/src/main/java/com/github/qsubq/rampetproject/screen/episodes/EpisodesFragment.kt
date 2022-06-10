package com.github.qsubq.rampetproject.screen.episodes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.github.qsubq.rampetproject.databinding.FragmentEpisodesBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodesFragment : Fragment() {
    private lateinit var binding: FragmentEpisodesBinding
    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        EpisodesAdapter()
    }
    private val viewModel: EpisodesViewModel by viewModels()


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
        val recyclerView: RecyclerView = binding.rcView
        recyclerView.adapter = adapter
    }

    private fun getEpisodes() {

        if (viewModel.episodesList.value == null) {
            if (viewModel.isOnline()) {
                viewModel.getAllEpisodes()
            } else {
                view?.let {
                    Snackbar.make(it, "Connection error", 5000)
                        .setAction("Try again") {
                            getEpisodes()
                        }
                        .show()
                }

            }
        }

        viewModel.episodesList.observe(viewLifecycleOwner) { list ->
            list.body()?.let { adapter.setList(it.results) }

        }

    }
}