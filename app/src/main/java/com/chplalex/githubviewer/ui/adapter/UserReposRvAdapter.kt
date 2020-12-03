package com.chplalex.githubviewer.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chplalex.githubviewer.R
import com.chplalex.githubviewer.mvp.presenter.list.IUserReposListPresenter
import com.chplalex.githubviewer.mvp.view.list.IUserReposItemView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_repo.view.*

class UserReposRvAdapter(
    val presenter: IUserReposListPresenter
) : RecyclerView.Adapter<UserReposRvAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_repo, parent, false)
        ).apply {
            containerView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        presenter.bindView(holder)
    }

    override fun getItemCount() = presenter.getCount()

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        IUserReposItemView, LayoutContainer {

        override var pos = -1

        override fun setName(name: String) = with(containerView) {
            txtRepoName.text = name
        }

        override fun setForksCount(forksCount: Int) = with(containerView) {
            txtRepoForksCount.text = forksCount.toString()
        }
    }

}