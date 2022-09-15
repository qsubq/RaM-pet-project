package com.github.qsubq.rampetproject.data.model.characterModel


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterModelItem(
    val created: String,
    val episode: ArrayList<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
) : Parcelable