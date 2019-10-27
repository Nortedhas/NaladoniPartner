package com.ageone.naladonipartner.Modules.Start

import com.ageone.naladonipartner.External.Interfaces.InterfaceModel
import com.ageone.naladonipartner.External.Interfaces.InterfaceViewModel

class StartViewModel : InterfaceViewModel {
    var model = StartModel()

    enum class EventType {
        OnCodePressed
    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()//TODO: change type data!
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is StartModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class StartModel : InterfaceModel {

}
