package com.chocoholic.project.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

object ChocoholicConstants {

    val job = Job()
    val projectScope = CoroutineScope(Dispatchers.Default + job)

}