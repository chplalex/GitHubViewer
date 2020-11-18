package com.chplalex.githubviewer.ui.patternspaghetti

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.chplalex.githubviewer.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FragmentPatternSpaghetti : Fragment() {

    private var text01: TextView? = null
    private var text02: TextView? = null
    private var text03: TextView? = null
    private var currentTextView: TextView? = null
    private var counter = 0

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_pattern, container, false).also {
        text01 = it.findViewById(R.id.text_01)
        text02 = it.findViewById(R.id.text_02)
        text03 = it.findViewById(R.id.text_03)

        savedInstanceState?.let {
            text01?.text = it.getCharSequence("text01")
            text02?.text = it.getCharSequence("text02")
            text03?.text = it.getCharSequence("text03")
            counter = it.getInt("counter")
            when (counter % 3) {
                0 -> currentTextView = text01
                1 -> currentTextView = text02
                2 -> currentTextView = text03
            }
        } ?: run {
            counter = 0
            text01?.text = "0"
            text02?.text = "0"
            text03?.text = "0"
            currentTextView = text01
        }

        val fab = activity?.findViewById<FloatingActionButton>(R.id.fab)
        fab?.setOnClickListener {
            counter++
            when (currentTextView) {
                text01 -> currentTextView = text02
                text02 -> currentTextView = text03
                text03 -> currentTextView = text01
            }
            currentTextView?.text = counter.toString()
        }
    }

    //TODO: непонятно, почему этот метод вызывается в том случае, если данный фрагмент неактивен
    // (когда мы работаем с другим фрагментов). Странно.
    // Более того, метод вызывается, когда данный фрагмент НЕ ИНИЦИАЛИЗИРОВАН
    // Надо бы разобраться
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        text01?.let { outState.putCharSequence("text01", it.text) }
        text02?.let { outState.putCharSequence("text02", it.text) }
        text03?.let { outState.putCharSequence("text03", it.text) }
        outState.putInt("counter", counter)
    }
}