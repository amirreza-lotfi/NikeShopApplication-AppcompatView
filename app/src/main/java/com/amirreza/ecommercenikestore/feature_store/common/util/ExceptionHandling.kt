package com.amirreza.feature_store.common.base

import org.json.JSONObject
import retrofit2.HttpException
import timber.log.Timber

data class NikeException(
    val typeOfError:ErrorType,
    val errorMessage:String,
):Throwable()

enum class ErrorType{
    SIMPLE,AUTH
}

fun mappingException(throwable: Throwable): NikeException {
    if(throwable is HttpException){
       try {
           val errorJsonObject = JSONObject(throwable.response()?.errorBody()!!.string())
           val errorMessage = errorJsonObject.getString("message")
           return NikeException(
               typeOfError = if(throwable.code() == 401 ) ErrorType.AUTH else ErrorType.SIMPLE,
               errorMessage = errorMessage
           )
       } catch (e:Exception){
           Timber.e(e)
       }
    }
    return NikeException(ErrorType.SIMPLE,"unknown error")
}