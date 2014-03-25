package models

/**
 * Created by Lunang on 23.03.14.
 */
//Model
case class Product(
          ean:Long, name:String, description:String
                    )
object Product {

  //DAO
  var products = Set(
    Product(5010255079763L,"Paperclips Large", "Large plain stack of 1000"),
    Product(1432876409987L,"Giant Paperclips", "Giant Plain 51mm 100 pack"),
    Product(5432987665432L,"Paperclip Giant PLain","Giant Plain pack of 10000"),
    Product(7543543298765L,"No Tear Paperclip","No Tear Extra large pack of 1000 "),
    Product(5437876512345L,"Zebra Paperclips","Zebra Length 28 mm Assorted 150 Pack")

  )
  //Finder function
  def findAll = products.toList.sortBy(_.ean)

  // Find by Ean
  def findbyEan(ean:Long) = products.find(_.ean == ean)

}
