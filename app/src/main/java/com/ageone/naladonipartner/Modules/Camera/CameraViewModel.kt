package com.ageone.naladonipartner.Modules.Camera

import com.ageone.naladonipartner.External.Interfaces.InterfaceModel
import com.ageone.naladonipartner.External.Interfaces.InterfaceViewModel

class CameraViewModel : InterfaceViewModel {
    var model = CameraModel()

    enum class EventType {

    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()//TODO: change type data!
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is CameraModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class CameraModel : InterfaceModel {

}
