package com.kumar.nychighschool.data

import com.kumar.nychighschool.domain.data.NycHighSchoolRepository

class FakeNycHighSchoolRepository : NycHighSchoolRepository {
    override suspend fun fetchHighSchools(): List<HighSchool> {
        return listOf(
            HighSchool(schoolName = "SchoolA"),
            HighSchool(schoolName = "SchoolB")
        )
    }

}