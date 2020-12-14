package com.chplalex.githubviewer.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.chplalex.githubviewer.R
import com.chplalex.githubviewer.mvp.presenter.list.IUsersListPresenter
import com.chplalex.githubviewer.mvp.view.list.IUserItemView
import com.chplalex.githubviewer.ui.App
import com.chplalex.githubviewer.ui.imageloader.IImageLoader
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_user.view.*
import javax.inject.Inject

class UsersRvAdapter(
    val presenter: IUsersListPresenter
) : RecyclerView.Adapter<UsersRvAdapter.ViewHolder>() {

    @Inject lateinit var imageLoader: IImageLoader<ImageView>

    init {
        App.instance.appСomponent.inject(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_user, parent, false)
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
        IUserItemView, LayoutContainer {

        override var pos = -1

        override fun setLogin(login: String) = with(containerView) {
            txtLogin.text = login
        }

        override fun loadAvatar(url: String) = with(containerView) {
            imageLoader.loadInto(url, imgAvatar)
        }
    }
}