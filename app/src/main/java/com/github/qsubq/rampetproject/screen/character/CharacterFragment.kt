package com.github.qsubq.rampetproject.screen.character

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.RecyclerView
import com.github.qsubq.rampetproject.R
import com.github.qsubq.rampetproject.adapter.CharacterAdapter
import com.github.qsubq.rampetproject.databinding.FragmentCharacterBinding
import kotlinx.coroutines.NonDisposableHandle.parent


class CharacterFragment : Fragment() {
    private lateinit var binding : FragmentCharacterBinding
    private lateinit var recyclerVIew : RecyclerView
    private lateinit var adapter : CharacterAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterBinding.inflate(layoutInflater,container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this).get(CharacterViewModel::class.java)
        recyclerVIew = binding.rvCharacters
        adapter = CharacterAdapter()
        recyclerVIew.adapter = adapter

        viewModel.getAllCharacters()
        viewModel.characterList.observe(viewLifecycleOwner){list ->
            list.body()?. let {adapter.setList(it)}
        }

    }
}