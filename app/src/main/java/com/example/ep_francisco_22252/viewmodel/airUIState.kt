package com.example.ep_francisco_22252.viewmodel

import com.example.ep_francisco_22252.data.Air

class airUIState (
    val Loading: Boolean = false,
    val airList: List<Air> = emptyList(),
    val errorMessage: String? = null
    )