package services

import com.google.inject.Singleton
import models.{Product, ProductPatch}
import play.api.inject.ApplicationLifecycle

import javax.inject.Inject

@Singleton
class ProductsMagazine{
  private var productList: Array[Product] = Array(
    new Product(1, "Apple"),
    new Product(2, "Banana"),
    new Product(3, "Pineapple"),
    new Product(4, "Owlbear")
  );

  def Add(product: Product): Unit = {
    productList = productList :+ product
  }

  def Delete(id: Int): Unit = {
    productList = productList.filter(prod => prod.id != id)
  }

  def Update(id: Int, productPatch: ProductPatch): Product = {
    val productToUpdate: Product = productList.find(prod => prod.id == id).get
    if(productPatch.name.isDefined) productToUpdate.name = productPatch.name.get
        productList.update(productList.indexWhere(elem => elem.id == id), productToUpdate)
    return productToUpdate
  }

  def Get(id: Option[Int] = None): Array[Product] = {
    if(id.isEmpty){
      return productList
    }
    return productList.filter(prod => prod.id == id.get);
  }
}
