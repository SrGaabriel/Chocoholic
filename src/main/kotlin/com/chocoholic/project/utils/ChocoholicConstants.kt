package com.chocoholic.project.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

object ChocoholicConstants {

    val job = Job()
    val projectScope = CoroutineScope(Dispatchers.Default + job)

    const val PREFIX = "?"
    const val GUILD_ID = 260119800947277824L

}