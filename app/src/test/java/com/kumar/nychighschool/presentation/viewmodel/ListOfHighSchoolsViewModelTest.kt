package com.kumar.nychighschool.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kumar.nychighschool.data.HighSchool
import com.kumar.nychighschool.domain.data.NycHighSchoolRepository
import com.kumar.nychighschool.getOrAwaitValue
import com.kumar.nychighschool.network.NetworkRequestStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.lang.Exception

class ListOfHighSchoolsViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    @Mock
    private lateinit var nycHighSchoolRepository: NycHighSchoolRepository

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun whenRepositoryReturnsListOfItems_uiStateVariableShouldHaveTheseItems() = runBlocking {
        Mockito.`when`(nycHighSchoolRepository.fetchHighSchools()).thenReturn(
            listOf(
                HighSchool(schoolName = "School A")
            )
        )
        val viewModel = ListOfHighSchoolsViewModel(nycHighSchoolRepository)
        testDispatcher.scheduler.advanceUntilIdle()
        val uiState = viewModel.uiState.getOrAwaitValue()

        assertEquals(uiState.networkRequestStatus.data?.first()?.schoolName, "School A")
    }

    @Test
    fun whenFetchHighSchoolsThrowsException_uiStateVariableShouldHaveNetworkStatusFailed() = runBlocking {
        Mockito.doThrow(Exception()).`when`(nycHighSchoolRepository).fetchHighSchools()
        val viewModel = ListOfHighSchoolsViewModel(nycHighSchoolRepository)
        testDispatcher.scheduler.advanceUntilIdle()
        val uiState = viewModel.uiState.getOrAwaitValue()
        assertTrue(uiState.networkRequestStatus is NetworkRequestStatus.Failed)
    }
}