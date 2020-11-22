package com.chplalex.githubviewer.ui.patternmvpmoxy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chplalex.githubviewer.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_pattern.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class FragmentPatternMvpMoxy : MvpAppCompatFragment(), IView {

    private val presenter by moxyPresenter {
        Presenter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_pattern, container, false).apply {
        activity?.findViewById<FloatingActionButton>(R.id.fab)?.setOnClickListener {
            presenter.fabClicked()
        }
    }

    override fun init() {
        text01.text = "0"
        text02.text = "0"
        text03.text = "0"
    }

    override fun onDataUpdated(index: Int, data: String) {
        when (index) {
            0 -> text01.text = data
            1 -> text02.text = data
            2 -> text03.text = data
        }
    }
}