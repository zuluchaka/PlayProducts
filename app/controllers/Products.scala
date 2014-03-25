package controllers

/**
 * Created by Lunang on 23.03.14.
 */
 import play.api.mvc.{Action,Controller}
import models.Product
import play.api.data.Form
import play.api.data.Forms.{mapping,longNumber,nonEmptyText}
import play.api.i18n.Messages



object Products extends Controller{

        private val productForm : Form[Product] = Form (

            mapping(
              "ean" -> longNumber.verifying("validation.ean.duplicate", Product.findbyEan(_).isEmpty),
              "name" -> nonEmptyText,
              "description" -> nonEmptyText
            )(Product.apply)(Product.unapply)
        )
        def list = Action {

              implicit  request =>
              val products = Product.findAll
              Ok(views.html.products.list(products))
        }

        def show(ean:Long) = Action {
              implicit request =>
              Product.findbyEan(ean).map{
                  product => Ok(views.html.products.details(product))
              }.getOrElse(NotFound)
        }
}
