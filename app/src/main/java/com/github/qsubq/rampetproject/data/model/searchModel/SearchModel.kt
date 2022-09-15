package com.github.qsubq.rampetproject.data.model.searchModel

import com.github.qsubq.rampetproject.data.model.characterModel.CharacterModelItem

data class SearchModel(
    val info: Info,
    val results: List<CharacterModelItem>,
)