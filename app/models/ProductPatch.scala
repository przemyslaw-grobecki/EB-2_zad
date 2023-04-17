package models

import play.api.libs.json.JsValue

class ProductPatch (jsonPatch: JsValue){
  var name: Option[String] = if((jsonPatch \ "name").isEmpty) None else
    Some((jsonPatch \ "name").get.toString().replaceAll("\"", ""))
}
