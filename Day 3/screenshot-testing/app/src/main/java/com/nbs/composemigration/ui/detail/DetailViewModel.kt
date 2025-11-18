package com.nbs.composemigration.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nbs.composemigration.model.DetailedScorerBio
import com.nbs.composemigration.model.TopScorer
import com.nbs.composemigration.model.detailedBios
import com.nbs.composemigration.model.topScorers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {

    private val _scorerDetail = MutableStateFlow<Pair<TopScorer, DetailedScorerBio>?>(null)
    val scorerDetail = _scorerDetail.asStateFlow()

    fun getDetail(id: Int) = viewModelScope.launch(Dispatchers.Default) {
        _scorerDetail.emit(
            Pair(
                first = topScorers.first { it.id == id},
                second = detailedBios.first { it.playerId == id }
            )
        )
    }
}