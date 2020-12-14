package com.chplalex.githubviewer.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.chplalex.githubviewer.R
import com.chplalex.githubviewer.TAG
import com.chplalex.githubviewer.mvp.model.entity.GithubUser
import com.chplalex.githubviewer.mvp.presenter.UserPresenter
import com.chplalex.githubviewer.mvp.view.UserView
import com.chplalex.githubviewer.ui.App
import com.chplalex.githubviewer.ui.BackButtonListener
import com.chplalex.githubviewer.ui.adapter.UserReposRvAdapter
import com.chplalex.githubviewer.ui.imageloader.IImageLoader
import kotlinx.android.synthetic.main.fragment_user.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class UserFragment() : MvpAppCompatFragment(), UserView, BackButtonListener {

    @Inject lateinit var imageLoader: IImageLoader<ImageView>

    companion object {
        private const val KEY = "USER_FRAGMENT_AGR_KEY"

        fun newInstance(user: GithubUser) = UserFragment().apply {
            arguments = Bundle().apply {
                putParcelable(KEY, user)
            }
        }
    }

    override fun init() {
        App.instance.appСomponent.inject(this)
        rvUserRepos.layoutManager = LinearLayoutManager(requireContext())
        rvUserRepos.adapter = adapter
    }

    private val presenter by moxyPresenter {
        val user = arguments?.getParcelable<GithubUser>(KEY)
            ?: throw RuntimeException("User Fragment has no argument")
        UserPresenter(user)
    }

    private val adapter by lazy {
        UserReposRvAdapter(presenter.userReposListPresenter)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = View.inflate(context, R.layout.fragment_user, null).apply {
        activity?.title = context.getString(R.string.fragment_title_user)
    }

    override fun setLogin(login: String) {
        txtUserLogin.text = login
    }

    override fun setAvatar(url: String) {
        imageLoader.loadInto(url, imgUserAvatar)
    }

    override fun updateReposList() {
        Log.d(TAG,"updateReposList(), repos count = ${presenter.userReposListPresenter.repos.size}")
        adapter.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backClick()
}