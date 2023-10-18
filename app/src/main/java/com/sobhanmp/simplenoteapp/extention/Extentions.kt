package com.sobhanmp.simplenoteapp.extention

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


fun <T>Fragment.collectFlow(flow: Flow<T>, action: (T) -> Unit){
    lifecycleScope.launch(Dispatchers.Main){
        repeatOnLifecycle(Lifecycle.State.STARTED){
            flow.collect{
                action(it)
            }
        }
    }
}