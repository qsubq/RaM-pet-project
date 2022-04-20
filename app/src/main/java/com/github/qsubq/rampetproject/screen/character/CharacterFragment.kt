package com.github.qsubq.rampetproject.screen.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.github.qsubq.rampetproject.APP
import com.github.qsubq.rampetproject.R
import com.github.qsubq.rampetproject.databinding.FragmentCharacterBinding
import com.github.qsubq.rampetproject.model.CharacterModelItem
import com.google.android.material.snackbar.Snackbar


class CharacterFragment : Fragment() {
    private lateinit var binding : FragmentCharacterBinding
    private lateinit var recyclerVIew : RecyclerView
    private lateinit var adapter : CharacterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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



        if (viewModel.characterList.value == null){
            val isOnline: Boolean = viewModel.getAllCharacters()
            if(!isOnline){
                view?.let { Snackbar.make(it, getString(R.string.snack_bar_text),6000).show() }
            }
        }


        viewModel.characterList.observe(viewLifecycleOwner){list ->
            list.body()?. let {adapter.setList(it)}
        }

        binding.SwipeRefreshLayout.setOnRefreshListener {
            viewModel.getAllCharacters()
            binding.SwipeRefreshLayout.isRefreshing = false
        }


    }

    companion object{
        fun onClickItem(characterModelItem: CharacterModelItem){
            val bundle = Bundle()
            bundle.putParcelable("character",characterModelItem)
            APP.navController.navigate(R.id.action_characterFragment_to_detailFragment, bundle)
        }
    }


}