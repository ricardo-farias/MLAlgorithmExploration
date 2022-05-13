package GrocerySelector

class GroceryItem(name: String, weight: Double, price: Double) {
  val itemName: String =  name
  val itemWeight: Double = weight // in ounces
  val itemPrice: Double = price

  override def toString: String = s"Item Name: ${itemName} at the price of ${itemPrice}"
}
