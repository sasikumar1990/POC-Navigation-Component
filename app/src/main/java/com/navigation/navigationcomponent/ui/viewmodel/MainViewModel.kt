package com.navigation.navigationcomponent.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.navigation.navigationcomponent.data.model.GetList
import com.navigation.navigationcomponent.data.repository.MainRepository
import com.navigation.navigationcomponent.data.status.Resource
import kotlinx.coroutines.launch


class MainViewModel @ViewModelInject constructor(private val mainRepository: MainRepository) : ViewModel() {

    private val _res = MutableLiveData<Resource<GetList>>()

    val res: LiveData<Resource<GetList>>
        get() = _res

    init {
        getList()
    }

    private fun getList() = viewModelScope.launch {
        _res.postValue(Resource.loading(null))
        mainRepository.getList().let {
            if (it.isSuccessful) {
                _res.postValue(Resource.success(it.body()))
            } else {
                _res.postValue(Resource.error(it.errorBody().toString(), null))
            }
        }
    }

}