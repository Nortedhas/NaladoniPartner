package com.ageone.naladonipartner.Modules.CodeInput

import com.ageone.naladonipartner.Application.utils
import com.ageone.naladonipartner.External.Interfaces.InterfaceModel
import com.ageone.naladonipartner.External.Interfaces.InterfaceViewModel

class CodeInputViewModel : InterfaceViewModel {
    var model = CodeInputModel()

    enum class EventType {

    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()//TODO: change type data!
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is CodeInputModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class CodeInputModel : InterfaceModel {
    var code = ""

}
