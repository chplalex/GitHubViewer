package com.chplalex.githubviewer.ui.patternmvp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.chplalex.githubviewer.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FragmentPatternMVP : Fragment(), MVPView {

    private var text01: TextView? = null
    private var text02: TextView? = null
    private var text03: TextView? = null

    private val mvpPresenter = MVPPresenter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_pattern, container, false).also { root ->

        text01 = root.findViewById<TextView>(R.id.text_01)
        text02 = root.findViewById<TextView>(R.id.text_02)
        text03 = root.findViewById<TextView>(R.id.text_03)

        savedInstanceState?.let {
            text01?.text = it.getCharSequence("text01")
            text02?.text = it.getCharSequence("text02")
            text03?.text = it.getCharSequence("text03")
        } ?: run {
            text01?.text = "0"
            text02?.text = "0"
            text03?.text = "0"
        }

        activity?.findViewById<FloatingActionButton>(R.id.fab)?.setOnClickListener {
            mvpPresenter.fabClicked()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        text01?.let { outState.putCharSequence("text01", it.text) }
        text02?.let { outState.putCharSequence("text02", it.text) }
        text03?.let { outState.putCharSequence("text03", it.text) }
    }

    override fun setText(index: Int, text: String) {
        when (index) {
            0 -> text01?.text = text
            1 -> text02?.text = text
            2 -> text03?.text = text
        }
    }
}