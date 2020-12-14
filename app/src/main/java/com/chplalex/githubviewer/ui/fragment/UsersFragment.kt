package com.chplalex.githubviewer.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.chplalex.githubviewer.ui.BackButtonListener
import com.chplalex.githubviewer.R
import com.chplalex.githubviewer.TAG
import com.chplalex.githubviewer.mvp.presenter.UsersPresenter
import com.chplalex.githubviewer.mvp.view.UsersView
import com.chplalex.githubviewer.ui.adapter.UsersRvAdapter
import com.chplalex.githubviewer.ui.imageloader.GlideImageLoader
import kotlinx.android.synthetic.main.fragment_users.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    companion object {
        fun newInstance() = UsersFragment()
    }

    private val presenter by moxyPresenter {
        UsersPresenter()
    }

    private val adapter by lazy {
        UsersRvAdapter(presenter.usersListPresenter, GlideImageLoader())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = View.inflate(context, R.layout.fragment_users, null).apply {
        activity?.title = context.getString(R.string.fragment_title_users)
    }

    override fun init() {
        rv_users.layoutManager = LinearLayoutManager(requireContext())
        rv_users.adapter = adapter
    }

    override fun updateUsersList() {
        adapter.notifyDataSetChanged()
    }

    override fun showExitMessage() {
        Log.d(TAG, "showExitMessage()")
        Toast.makeText(
            activity,
            "Для выхода из приложения нажмите назад еще один раз",
            Toast.LENGTH_SHORT
        )
    }

    override fun backPressed() = presenter.backClick()

}