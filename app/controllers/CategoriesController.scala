package controllers

import javax.inject._
import play.api._
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._
import services.ProductsMagazine

@Singleton
class CategoriesController @Inject()(val magazine: ProductsMagazine, val controllerComponents: ControllerComponents) extends BaseController {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def getCategories: Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok("Ok")
  }

  def getCategory(id: Int): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok("Ok")
  }

  def addCategory(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok("Ok")
  }

  def updateCategory(id: Int): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok("Ok")
  }

  def deleteCategory(id: Int): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok("Ok")
  }

}
