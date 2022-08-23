package com.example.routings.city

import com.example.repository.CityRepository
import com.example.utils.Routes
import com.example.utils.ServiceResult
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.deleteCities(
    cityRepository: CityRepository
) {
    route(Routes.CITY_ROUTE) {
        delete(Routes.DELETE_ROUTE) {

            val id = call.parameters["id"]!!.toInt()
            id.let {
                cityRepository.deleteCityById(id).let { citiesResponse ->
                    when(citiesResponse) {
                        is ServiceResult.Success -> {
                            call.respond(
                                status = HttpStatusCode.OK,
                                message = citiesResponse.data
                            )
                        }
                        is ServiceResult.Error -> {
                            println("Error! No Cities received from database")
                            call.respond(
                                status = HttpStatusCode.BadRequest,
                                message = citiesResponse.errorCode
                            )
                        }
                    }
                }
            }
        }

        delete("/{state_id}/delete") {

            val stateId = call.parameters["state_id"]!!.toInt()
            stateId.let {
                cityRepository.deleteCitiesOfState(stateId).let { citiesResponse ->
                    when(citiesResponse) {
                        is ServiceResult.Success -> {
                            call.respond(
                                status = HttpStatusCode.OK,
                                message = citiesResponse.data
                            )
                        }
                        is ServiceResult.Error -> {
                            println("Error! No Cities received from database")
                            call.respond(
                                status = HttpStatusCode.BadRequest,
                                message = citiesResponse.errorCode
                            )
                        }
                    }
                }
            }
        }

        delete("/") {
            cityRepository.deleteCities().let { citiesResponse ->
                when(citiesResponse) {
                    is ServiceResult.Success -> {
                        call.respond(
                            status = HttpStatusCode.OK,
                            message = citiesResponse.data
                        )
                    }
                    is ServiceResult.Error -> {
                        println("Error! No Cities received from database")
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