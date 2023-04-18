package models

import play.api.libs.json.{Json, OFormat}

case class Category(id: Int, name: String)
{
  var products: List[Product] = _
}
object Category {
  implicit val writes: OFormat[Product] = Json.format[Product];
}