package com.kumar.nychighschool.presentation.viewmodel

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kumar.nychighschool.domain.data.NycHighSchoolRepository
import com.kumar.nychighschool.presentation.screen.ListOfHighSchoolsScreen
import kotlinx.coroutines.test.runTest
import com.kumar.nychighschool.data.FakeNycHighSchoolRepository
import com.kumar.nychighschool.data.HighSchool
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn

@RunWith(MockitoJUnitRunner::class)
class ListOfHighSchoolsViewModelTest {

    private lateinit var listOfHighSchoolsViewModel: ListOfHighSchoolsViewModel

    @Mock
    private lateinit var nycHighSchoolRepository: NycHighSchoolRepository

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        listOfHighSchoolsViewModel = ListOfHighSchoolsViewModel(nycHighSchoolRepository)
    }

    @Test
    fun whenDataIsFetchedSuccessfully_appShouldShowItemsOnScreen() = runTest {
        doReturn(
            listOf(
                HighSchool(schoolName = "SchoolA"),
                HighSchool(schoolName = "SchoolB")
            )
        ).`when`(nycHighSchoolRepository).fetchHighSchools()


        composeTestRule.setContent {
            ListOfHighSchoolsScreen(listOfHighSchoolsViewModel)
        }
        // Having
        // As the fake repository instantly returns the items
        composeTestRule.onNodeWithTag("School Item").assertExists()
    }

    @After
    fun tearDown() {
    }
}