package com.chplalex.githubviewer.di.modules

import android.widget.ImageView
import com.chplalex.githubviewer.di.scopes.UsersScope
import com.chplalex.githubviewer.ui.imageloader.GlideImageLoader
import com.chplalex.githubviewer.ui.imageloader.IImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ImageModule {
    @Singleton
    @Provides
    fun imageLoader(): IImageLoader<ImageView> = GlideImageLoader()
}