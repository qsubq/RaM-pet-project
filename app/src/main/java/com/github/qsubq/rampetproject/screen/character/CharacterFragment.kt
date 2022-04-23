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
import com.github.qsubq.rampetproject.model.characterModel.CharacterModelItem


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
        getCharacter()
    }

    private fun init() {
        recyclerVIew = binding.rvCharacters
        adapter = CharacterAdapter()
        recyclerVIew.adapter = adapter
    }

    private fun getCharacter(){
        val viewModel = ViewModelProvider(this).get(CharacterViewModel::class.java)

        if (viewModel.characterList.value == null){
            if(viewModel.isOnline()){
                viewModel.getRandomCharacters()
            }
            else{
                binding.rvCharacters.visibility = View.GONE
            }
        }

        viewModel.characterList.observe(viewLifecycleOwner){list ->
            list.body()?. let {adapter.setList(it)}
        }

        binding.SwipeRefreshLayout.setOnRefreshListener {
            viewModel.getRandomCharacters()
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