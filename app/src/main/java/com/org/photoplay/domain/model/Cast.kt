package com.org.photoplay.domain.model

data class Cast(
    val id: Int,
    val cast: List<CastMember>
)

data class CastMember(
    val id: Int,
    val name: String,
    val profile_path: String,
    val character: String
)