package controllers

import play.api.libs.json.{JsValue, Json, OFormat}
import play.api.mvc.{AnyContent, BaseController, ControllerComponents, Request}

import javax.inject.{Inject, Singleton}

case class Product(var id: Int, var name: String);
object Product {
  implicit val writes: OFormat[Product] = Json.format[Product];
}

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class ProductsController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {
  private var productList: Array[Product] = Array(
    new Product(1, "Apple"),
    new Product(2, "Banana"),
    new Product(3, "Pineapple"),
    new Product(4, "Owlbear")
  );


  def products() = Action { implicit request: Request[AnyContent] =>
    Ok(Json.toJson(productList))
  }

  def product(id : Int) = Action { implicit request: Request[AnyContent] =>
    Ok(Json.toJson(productList.filter(p => p.id == id)))
  }

  def addProduct() = Action { implicit request: Request[AnyContent] =>
    val body: AnyContent = request.body
    val jsonBody: JsValue = body.asJson.get
    val productBody: Product = Json.fromJson[Product](jsonBody).get
    productList = productList :+ productBody
    Ok(jsonBody)
  }

  def updateProduct(id: Int) = Action { implicit request: Request[AnyContent] =>
    val body: AnyContent = request.body
    val jsonBody: JsValue = body.asJson.get
    val nameUpdate = (jsonBody \ "name").get.as[String]
    val newProduct = new Product(id, nameUpdate)
    productList.update(productList.indexWhere(elem => elem.id == id), newProduct)
    Ok(Json.toJson(newProduct))
  }

  def deleteProduct(id: Int) = Action { implicit request: Request[AnyContent] =>
    productList = productList.filter(prod => prod.id != id)
    Ok(Json.toJson(productList))
  }
}
