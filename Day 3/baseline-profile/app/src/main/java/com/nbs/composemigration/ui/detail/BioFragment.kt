package com.nbs.composemigration.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.nbs.composemigration.databinding.FragmentBioBinding
import kotlinx.coroutines.launch

class BioFragment : Fragment() {

    private var _binding: FragmentBioBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentBioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.scorerDetail.collect { detail ->
                    detail?.let { (scorer, bio) ->
                        binding.tvNationalityValue.text = bio.nationality
                        binding.tvPreferredFootValue.text = bio.preferredFoot
                        binding.tvDobValue.text = bio.dateOfBirth
                        binding.tvAppearancesValue.text = bio.appearances.toString()
                        binding.tvGoalsValue.text = scorer.goal.toString()
                        binding.tvAssistsValue.text = bio.assists.toString()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}