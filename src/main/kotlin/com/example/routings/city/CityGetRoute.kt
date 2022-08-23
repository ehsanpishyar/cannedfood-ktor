package com.example.routings.city

import com.example.repository.CityRepository
import com.example.utils.Routes
import com.example.utils.ServiceResult
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getCities(
    cityRepository: CityRepository
) {
    route(Routes.CITY_ROUTE) {
        get("/") {

            val params = call.request.rawQueryParameters
            val stateId = params["state_id"]?.toInt()
            val id = params["id"]?.toInt()
            val title = params["title"]

            if (stateId != null && id == null && title == null) {
                cityRepository.getCities(stateId).let { citiesResponse ->
                    when(citiesResponse) {
                        is ServiceResult.Success -> {
                            call.respond(
                                status = HttpStatusCode.OK,
                                message = citiesResponse.data
                            )
                        }
                        is ServiceResult.Error -> {
                            println("Error! City not found or state id you entered is invalid")
                            call.respond(
                                status = HttpStatusCode.BadRequest,
                                message = citiesResponse.errorCode
                            )
                        }
                    }
                }
            }

            if (stateId != null && id != null) {
                cityRepository.getCityById(id).let { cityResponse ->
                    when(cityResponse) {
                        is ServiceResult.Success -> {
                            call.respond(
                                status = HttpStatusCode.OK,
                                message = cityResponse.data!!
                            )
                        }
                        is ServiceResult.Error -> {
                            println("Error! City not found")
                            call.respond(
                                status = HttpStatusCode.BadRequest,
                                message = cityResponse.errorCode
                            )
                        }
                    }
                }
            }

            if (stateId != null && title != null) {
                cityRepository.getCitiesByTitle(title).let { citiesResponse ->
                    when(citiesResponse) {
                        is ServiceResult.Success -> {
                            call.respond(
                                status = HttpStatusCode.OK,
                                message = citiesResponse.data
                            )
                        }
                        is ServiceResult.Error -> {
                            println("Error! City not found")
                            call.respond(
                                status = HttpStatusCode.BadRequest,
                                message = citiesResponse.errorCode
                            )
                        }
                    }
                }
            }
        }
    }
}