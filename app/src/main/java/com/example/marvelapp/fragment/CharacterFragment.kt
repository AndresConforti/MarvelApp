package com.example.marvelapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.example.domain.entity.Character
import com.example.domain.utils.Constants.CHARACTER_ID
import com.example.marvelapp.R
import com.example.marvelapp.databinding.CharacterFragmentBinding
import com.example.marvelapp.mvvm.viewmodel.FragmentViewModel
import org.koin.android.ext.android.inject
import org.koin.core.component.KoinComponent

class CharacterFragment : DialogFragment(), KoinComponent {
    private lateinit var binding: CharacterFragmentBinding
    private val viewModel: FragmentViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CharacterFragmentBinding.inflate(layoutInflater)
        viewModel.fragmentState.observe({ lifecycle }, ::fragmentState)
        viewModel.getCharacter(arguments?.getString(CHARACTER_ID).orEmpty())
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        binding.fragment.setOnClickListener { viewModel.onTouchListener() }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    private fun fragmentState(characterDetailData: FragmentViewModel.FragmentData) {
        when (characterDetailData.state) {
            FragmentViewModel.FragmentState.RESPONSE_SUCCESS -> displayFragment(characterDetailData.data)
            FragmentViewModel.FragmentState.RESPONSE_ERROR -> displayError(R.string.characters_activity_get_error)
            FragmentViewModel.FragmentState.DISMISS_FRAGMENT -> dismiss()
        }
    }

    private fun displayFragment(id: Character?) {
        id.let {
            binding.apply {
                this.fragmentName.text = it?.name
                this.fragmentDescription.text =
                    if (it?.description.isNullOrEmpty()) binding.fragmentDescription.text else it?.description
                Glide
                    .with(this@CharacterFragment)
                    .load(it?.img)
                    .into(this.fragmentImg)
            }
        }
    }

    private fun displayError(stringId: Int) {
        binding.fragmentImg.visibility = View.GONE
        binding.fragmentDescription.visibility = View.GONE
        binding.fragmentName.text = getString(stringId)
    }

    companion object {
        const val TAG = "CHARACTER_FRAGMENT"

        fun newInstance(id: String): CharacterFragment {
            val args = Bundle()
            args.putString(CHARACTER_ID, id)
            val fragment = CharacterFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
