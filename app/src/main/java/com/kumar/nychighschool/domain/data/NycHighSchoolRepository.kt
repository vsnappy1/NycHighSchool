package com.kumar.nychighschool.domain.data

import com.kumar.nychighschool.data.HighSchool
import kotlin.jvm.Throws

interface NycHighSchoolRepository {
    @Throws(Exception::class)
    suspend fun fetchHighSchools(): List<HighSchool>
}