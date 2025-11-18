package com.nbs.composemigration.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.compose.content
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nbs.composemigration.ui.component.PlayerCard
import com.nbs.composemigration.ui.theme.AppTheme

class PlayerCardFragment : Fragment() {

    private val viewModel: DetailViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = content {
        AppTheme {
            val details by viewModel.scorerDetail.collectAsStateWithLifecycle(initialValue = null)

            details?.let { (topScorer, detailedBio) ->

                PlayerCard(
                    scorer = topScorer,
                    bio = detailedBio,
                )
            }
        }
    }
}