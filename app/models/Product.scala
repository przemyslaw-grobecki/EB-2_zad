package models

import play.api.libs.json.{Json, OFormat}

case class Product(var id: Int, var name: String);
object Product {
  implicit val writes: OFormat[Product] = Json.format[Product];
}