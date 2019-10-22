package com.ageone.naladonipartner.Modules

import com.ageone.naladonipartner.Application.api
import com.ageone.naladonipartner.Application.webSocket
import com.ageone.naladonipartner.External.Interfaces.InterfaceModel
import com.ageone.naladonipartner.External.Interfaces.InterfaceViewModel
import com.ageone.naladonipartner.Models.User.user
import timber.log.Timber

class LoadingViewModel : InterfaceViewModel {
    var model = LoadingModel()

    enum class EventType{
        onFinish
    }

    fun initialize(recievedModel: InterfaceModel, completion: ()->(Unit)) {
        if (recievedModel is LoadingModel) {
            model = recievedModel
            completion.invoke()
        }
    }

    fun startLoading(completion: () -> Unit) {
      /*  user.info.city?.let { city ->
            api.getCityStocks(city.hashId)
        }
        api.requestMainLoad {
            Timber.i("completion invoke")
            webSocket.initialize()
        }*/
        completion.invoke()


    }
}

class LoadingModel : InterfaceModel {

}
