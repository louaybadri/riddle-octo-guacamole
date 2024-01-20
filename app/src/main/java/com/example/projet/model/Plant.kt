package com.example.projet.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Plant(
    @JsonProperty("id")
    val id: Int,

    @JsonProperty("id")
    val image_url: String,

    @JsonProperty("id")
    val common_name: String,
    )