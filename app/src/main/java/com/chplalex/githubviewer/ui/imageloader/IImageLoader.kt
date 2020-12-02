package com.chplalex.githubviewer.ui.imageloader

interface IImageLoader <T> {
    fun loadInto(url: String, container: T)
}