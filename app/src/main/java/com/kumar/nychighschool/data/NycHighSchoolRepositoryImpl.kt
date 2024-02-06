package com.kumar.nychighschool.data

import com.kumar.nychighschool.domain.data.NycHighSchoolRepository
import com.kumar.nychighschool.network.RestClient

class NycHighSchoolRepositoryImpl : NycHighSchoolRepository {

    override suspend fun fetchHighSchools(): List<HighSchool> {
        return RestClient.getCityOfNewYorkApi().getListOfHighSchools()
    }
}