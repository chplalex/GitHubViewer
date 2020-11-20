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

    private lateinit var text01: TextView
    private lateinit var text02: TextView
    private lateinit var text03: TextView

    private val mvpPresenter = MVPPresenter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_pattern, container, false).apply {

        text01 = findViewById(R.id.text_01)
        text02 = findViewById(R.id.text_02)
        text03 = findViewById(R.id.text_03)

        savedInstanceState?.let {
            text01.text = it.getCharSequence("text01")
            text02.text = it.getCharSequence("text02")
            text03.text = it.getCharSequence("text03")
        } ?: run {
            text01.text = "0"
            text02.text = "0"
            text03.text = "0"
        }

        activity?.findViewById<FloatingActionButton>(R.id.fab)?.setOnClickListener {
            mvpPresenter.fabClicked()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (this::text01.isInitialized && this::text02.isInitialized && this::text03.isInitialized) {
            outState.putCharSequence("text01", text01.text)
            outState.putCharSequence("text02", text02.text)
            outState.putCharSequence("text03", text03.text)
        }
    }

    override fun setText(index: Int, text: String) {
        when (index) {
            0 -> text01.text = text
            1 -> text02.text = text
            2 -> text03.text = text
        }
    }
}