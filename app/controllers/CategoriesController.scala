package controllers

import javax.inject._
import play.api._
import play.api.mvc._

@Singleton
class CategoriesController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def categories() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }


}
