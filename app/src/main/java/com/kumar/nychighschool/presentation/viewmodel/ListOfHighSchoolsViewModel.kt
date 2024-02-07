package com.kumar.nychighschool.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kumar.nychighschool.data.HighSchool
import com.kumar.nychighschool.data.NycHighSchoolRepositoryImpl
import com.kumar.nychighschool.domain.data.NycHighSchoolRepository
import com.kumar.nychighschool.network.NetworkRequestStatus
import com.kumar.nychighschool.presentation.screen.ListOfHighSchoolsUiState
import kotlinx.coroutines.launch

class ListOfHighSchoolsViewModel(
    val nycHighSchoolRepository: NycHighSchoolRepository = NycHighSchoolRepositoryImpl()
) : ViewModel() {

    private var _uiState: MutableLiveData<ListOfHighSchoolsUiState> = MutableLiveData(
        ListOfHighSchoolsUiState()
    )
    val uiState: LiveData<ListOfHighSchoolsUiState> = _uiState

    init {
        fetchHighSchools()
    }

    // No public method here, lets do integration test
    private fun fetchHighSchools() {
        viewModelScope.launch {
            _uiState.postValue(_uiState.value?.copy(networkRequestStatus = NetworkRequestStatus.Loading()))
            try {
                val listOfHighSchool = nycHighSchoolRepository.fetchHighSchools()
                _uiState.postValue(
                    _uiState.value?.copy(
                        networkRequestStatus = NetworkRequestStatus.Succeed(listOfHighSchool)
                    )
                )
            } catch (e: Exception) {
                _uiState.postValue(
                    _uiState.value?.copy(
                        networkRequestStatus = NetworkRequestStatus.Failed(
                            "Failed to fetch schools",
                            emptyList<HighSchool>()
                        )
                    )
                )
            }
        }
    }
}