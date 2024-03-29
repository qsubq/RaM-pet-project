package com.github.qsubq.rampetproject.data.model.episodeModel

data class Result(
    val air_date: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    var id: Int,
    val name: String,
    val url: String
)