package com.kumar.nychighschool.domain.data

import com.kumar.nychighschool.data.HighSchool

interface NycHighSchoolRepository {
    suspend fun fetchHighSchools(): List<HighSchool>
}