package controllers

import play.api.libs.json.{JsValue, Json, OFormat}
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents, Request}

import javax.inject.{Inject, Singleton}
import models.{Product, ProductPatch}
import services.ProductsMagazine

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class ProductsController @Inject()(val magazine: ProductsMagazine,
                                   val controllerComponents: ControllerComponents) extends BaseController {
  def products(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(Json.toJson(magazine.Get()))
  }

  def product(id : Int): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(Json.toJson(magazine.Get(Some(id))))
  }

  def addProduct(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    val body: AnyContent = request.body
    val jsonBody: JsValue = body.asJson.get
    val productBody: Product = Json.fromJson[Product](jsonBody).get
    magazine.Add(productBody);
    Ok(jsonBody)
  }

  def updateProduct(id: Int): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    val body: AnyContent = request.body
    val jsonBody: JsValue = body.asJson.get
    val productPatch = new ProductPatch(jsonBody)
    val updatedProduct = magazine.Update(id, productPatch)
    Ok(Json.toJson(updatedProduct))
  }

  def deleteProduct(id: Int): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    magazine.Delete(id)
    Ok("Deleted product with id " + id)
  }
}
