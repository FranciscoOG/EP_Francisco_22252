package com.example.ep_francisco_22252

import kotlinx.serialization.Serializable

@Serializable
object FirstScreenRoute

@Serializable
data class SecondScreenRoute(val lat: Double, val long: Double)