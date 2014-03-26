package controllers

/**
 * Created by Lunang on 23.03.14.
 */
 import play.api.mvc.{Action,Controller}
import models.Product
import play.api.data.Form
import play.api.data.Forms.{mapping,longNumber,nonEmptyText}
import play.api.i18n.Messages
import play.api.mvc.Flash



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


        def save = Action {
              implicit  request =>
              val newProductForm = productForm.bindFromRequest()

              newProductForm.fold{
                  hasErrors = {
                      Redirect(routes.Products.newProduct()).
                        flashing(Flash(form.data) +
                        ("error" -> Messages("validation.errors")))
                  },
                  success = {
                    newProduct =>
                      Product.add(newProduct)
                      val message = Messages("products.new.success",newProcuct.name)
                      Redirect(routes.Products.show(newProduct.ean)).
                        flashing("success" -> message)
                  }
              }
        }
}
