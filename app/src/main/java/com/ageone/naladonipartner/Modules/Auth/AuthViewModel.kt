package com.ageone.naladonipartner.Modules.Auth

import com.ageone.naladonipartner.Application.utils
import com.ageone.naladonipartner.External.Interfaces.InterfaceModel
import com.ageone.naladonipartner.External.Interfaces.InterfaceViewModel

class AuthViewModel : InterfaceViewModel {
    var model = AuthModel()

    enum class EventType {
        OnNextPressed
    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()//TODO: change type data!
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is AuthModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class AuthModel : InterfaceModel {
    var code = ""
}
