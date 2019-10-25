package com.ageone.naladonipartner.Modules.CodeInput

import com.ageone.naladonipartner.External.Interfaces.InterfaceModel
import com.ageone.naladonipartner.External.Interfaces.InterfaceViewModel
import com.ageone.naladonipartner.External.Libraries.Alert.alertManager
import com.ageone.naladonipartner.External.Libraries.Alert.single
import timber.log.Timber

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

    fun validate(competition: () -> Unit){
        if(model.code.length < 9){
            alertManager.single(
                "Ошибка",
                "Неверный QR-код",
                null,
                "Понятно"
            )
            { _, position -> }
            return
        }
        competition.invoke()
    }
}

class CodeInputModel : InterfaceModel {
    var code = ""

}
