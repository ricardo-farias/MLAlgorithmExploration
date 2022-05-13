package GrocerySelector

import scala.util.Random

class GroceryAssistant(money: Double) {
  // Genetic Algorithm that generates

  var shoppingCart: List[GroceryItem] = List()

  def fitness(): Double = {
    var totalPrice: Double = 0.0
    var totalWeight: Double = 0.0
    for (item <- shoppingCart){
      totalPrice += item.itemPrice
      totalWeight += item.itemWeight
    }
    if (money - totalPrice < 0.0) 0.0
    else (money - totalPrice) + totalPrice + totalWeight + shoppingCart.length
  }

  def randomSelection(): GroceryAssistant = {
    shoppingCart = Catalog.choose(5)
    this
  }

  def selection(groceryList: List[GroceryItem]): Unit = {
    shoppingCart = groceryList
  }

  def mutate(position: Integer, value: GroceryItem): Unit = {
    shoppingCart = shoppingCart.updated(position, value)
  }

  override def toString: String = s"Fitness: ${fitness()}"

}








