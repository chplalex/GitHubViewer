package com.chplalex.githubviewer.mvp.model.entity

import com.google.gson.annotations.Expose

data class GithubUserRepo (
    @Expose val id: Int,
    @Expose val name: String,
    @Expose val htmlUrl: String,
    @Expose val createdAt: String,
    @Expose val updatedAt: String,
    @Expose val pushedAt: String,
    @Expose val forksCount: Int)