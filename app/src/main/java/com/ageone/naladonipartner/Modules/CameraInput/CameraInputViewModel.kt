package com.ageone.naladonipartner.Modules.CameraInput

import com.ageone.naladonipartner.Application.utils
import com.ageone.naladonipartner.External.Interfaces.InterfaceModel
import com.ageone.naladonipartner.External.Interfaces.InterfaceViewModel

class CameraInputViewModel : InterfaceViewModel {
    var model = CameraInputModel()

    enum class EventType {
        OnCameraPressed
    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()//TODO: change type data!
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is CameraInputModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class CameraInputModel : InterfaceModel {

}
