package com.chplalex.githubviewer.mvp.model.entity.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomGithubUser(
    @PrimaryKey
    var id: String,
    var login: String,
    var avatarUrl: String,
    var reposUrl: String
)