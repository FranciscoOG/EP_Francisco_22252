package com.example.ep_francisco_22252.data

data class City(
    val name: String,
    val latitude: Double,
    val longitude: Double
)
val cities = listOf(
    City("Braga", 41.5518, -8.4229),
    City("Porto", 41.1496, -8.6109),
    City("Coimbra", 40.2033, -8.4103),
    City("Lisboa", 38.7223, -9.1393),
    City("Évora", 38.5714, -7.9135),
    City("Faro", 37.0194, -7.9304)
)