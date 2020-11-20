package com.chplalex.githubviewer.ui.patternmvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.chplalex.githubviewer.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FragmentPatternMVVM : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_pattern, container, false).also { root ->

        val text01 = root.findViewById<TextView>(R.id.text_01)
        val text02 = root.findViewById<TextView>(R.id.text_02)
        val text03 = root.findViewById<TextView>(R.id.text_03)

        ViewModelProvider(this).get(MVVMViewModel::class.java).apply {
            liveData01.observe(viewLifecycleOwner) { text01.text = it }
            liveData02.observe(viewLifecycleOwner) { text02.text = it }
            liveData03.observe(viewLifecycleOwner) { text03.text = it }
            activity?.findViewById<FloatingActionButton>(R.id.fab)?.setOnClickListener { fabClicked() }
        }
    }
}