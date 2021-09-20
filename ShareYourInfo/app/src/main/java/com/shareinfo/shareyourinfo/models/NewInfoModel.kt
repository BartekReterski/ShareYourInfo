package com.shareinfo.shareyourinfo.models

import io.realm.RealmObject
import java.util.*

 open class NewInfoModel(): RealmObject() {

    var socialMediaName:String?=null
    var socialMediaNameValue:String?=null
    var colorPickedHex:String?=null
    var visibleItem:Boolean?=true
}