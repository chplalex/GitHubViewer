package com.chplalex.githubviewer.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.chplalex.githubviewer.ui.App
import com.chplalex.githubviewer.ui.BackButtonListener
import com.chplalex.githubviewer.R
import com.chplalex.githubviewer.mvp.model.repo.GithubUsersRepo
import com.chplalex.githubviewer.mvp.presenter.UsersPresenter
import com.chplalex.githubviewer.mvp.view.UsersView
import com.chplalex.githubviewer.ui.adapter.UsersRvAdapter
import kotlinx.android.synthetic.main.fragment_users.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    companion object {
        fun newInstance() = UsersFragment()
    }

    private val presenter by moxyPresenter {
        UsersPresenter(App.instance.router, GithubUsersRepo())
    }

    private val adapter by lazy {
        UsersRvAdapter(presenter.usersListPresenter)
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

    override fun backPressed() = presenter.backClick()

}