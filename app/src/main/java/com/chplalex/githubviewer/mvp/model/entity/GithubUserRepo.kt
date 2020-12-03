package com.chplalex.githubviewer.mvp.model.entity

import com.google.gson.annotations.Expose

data class GithubUserRepo (
    @Expose val id: Int,
    @Expose val name: String,
    @Expose val htmlUrl: String,
    val createdAt: Long,
    val updatedAt: Long,
    val pushedAt: Long,
    @Expose val forksCount: Int)