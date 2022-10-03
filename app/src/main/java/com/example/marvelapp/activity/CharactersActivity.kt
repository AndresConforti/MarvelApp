package com.example.marvelapp.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.marvelapp.R
import com.example.marvelapp.adapter.CharacterAdapter
import com.example.marvelapp.adapter.CharacterListener
import com.example.marvelapp.databinding.ActivityCharactersBinding
import com.example.marvelapp.entity.Character
import com.example.marvelapp.fragment.CharacterFragment
import com.example.marvelapp.mvvm.viewmodel.CharactersViewModel
import com.example.marvelapp.util.Constants
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CharactersActivity : AppCompatActivity(), KoinComponent, CharacterListener {
    private lateinit var binding: ActivityCharactersBinding
    val viewModel: CharactersViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getCharacters()
        viewModel.characterState.observe({ lifecycle }, ::charactersState)
    }

    private fun charactersState(CharacterData: CharactersViewModel.CharactersData) {
        when (CharacterData.characterState) {
            CharactersViewModel.CharactersState.LOADING -> displayLoader()
            CharactersViewModel.CharactersState.SHOW_CHARACTERS -> displayCharacters(CharacterData.characterInformation)
            CharactersViewModel.CharactersState.CHARACTERS_NOT_FOUND -> displayError(R.string.characters_activity_get_error)
            CharactersViewModel.CharactersState.DISPLAY_FRAGMENT -> displayFragment(CharacterData.characterId)
        }
    }

    private fun displayLoader() {
        binding.charactersActivityLoader.visibility = View.VISIBLE
    }

    private fun displayCharacters(charactersList: List<Character>) {
        binding.charactersActivityTitle.visibility = View.VISIBLE
        binding.charactersActivityLoader.visibility = View.GONE
        binding.charactersActivityRecyclerview.adapter = CharacterAdapter(charactersList, this)
    }

    private fun displayError(stringId: Int) {
        binding.charactersActivityLoader.visibility = View.GONE
        binding.charactersActivityError.text = getString(stringId)
    }

    override fun setOnClickListener(characterId: String) {
        viewModel.showFragment(characterId)
    }

    private fun displayFragment(id: String) {
        val characterFragment = CharacterFragment.newInstance(id)
        val fragmentManager: FragmentManager = supportFragmentManager
        characterFragment.show(fragmentManager, Constants.TAG)
    }

    companion object {
        fun newInstance(context: Context): Intent = Intent(context, CharactersActivity::class.java)
    }
}
