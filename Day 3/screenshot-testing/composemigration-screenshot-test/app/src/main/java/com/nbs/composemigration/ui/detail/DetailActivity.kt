package com.nbs.composemigration.ui.detail

import android.graphics.Color
import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.nbs.composemigration.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private val viewModel: DetailViewModel by viewModels()

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.dark(Color.TRANSPARENT),
        )
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val scorerId = intent.getIntExtra(EXTRA_SCORER_ID, -1)

        if (scorerId == -1) {
            finish()
            return
        }

        // 1. Call the ViewModel to load data immediately
        viewModel.getDetail(scorerId)

        if (supportFragmentManager.findFragmentById(binding.fragmentPlayerCardContainer.id) == null) {
            supportFragmentManager.commit {
                replace<PlayerCardFragment>(binding.fragmentPlayerCardContainer.id)
            }
        }

        if (supportFragmentManager.findFragmentById(binding.fragmentBioContainer.id) == null) {
            supportFragmentManager.commit {
                replace<BioFragment>(binding.fragmentBioContainer.id)
            }
        }
    }

    companion object {
        const val EXTRA_SCORER_ID = "extra_scorer_id"
    }
}