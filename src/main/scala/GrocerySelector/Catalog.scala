package GrocerySelector

import scala.util.Random

object Catalog {
  val catalog: List[GroceryItem] = List(
    // Cereal
    new GroceryItem("Frosted Flakes", 24, 4.99),
    new GroceryItem("Froot Loops", 19.4, 5.49),
    new GroceryItem("Apple Jacks", 19.4, 5.49),
    new GroceryItem("Cinnamon Toast Crunch", 16.8, 5.99),
    new GroceryItem("Honey Nut Cheerios",15.4, 5.99),
    new GroceryItem("Lucky Charms",10.5, 5.99),
    // Produce
    new GroceryItem("Yellow Banana", 44.8, 1.65),
    new GroceryItem("Blue Berries", 6, 2.50),
    new GroceryItem("Strawberries", 8, 2.50),
    new GroceryItem("Key Limes", 32,2.50),
    new GroceryItem("Navel Orange", 48,3.99),
    new GroceryItem("Mangos", 35, 4.00),
    new GroceryItem("Granny Smith Apples", 80, 8.95),
    new GroceryItem("Fresh Lemons", 32,3.99),
    new GroceryItem("Fresh Cantaloupe Melon", 48,4.99),
    new GroceryItem("Mandarins", 48, 5.99),
    // Animal Products
    new GroceryItem("Hardwood Smoked Bacon", 16, 6.99),
    new GroceryItem("2% Milk Gallon", 128, 2.99),
    new GroceryItem("Grade A Large Eggs Dozen", 24, 2.50),
    new GroceryItem("Philadelphia Cream Cheese", 8, 3.29),
    new GroceryItem("Large Organic Brown Eggs 18 pk", 38, 4.99),
    new GroceryItem("Yoplait Strawberry Yogurt", 6, .79),
    new GroceryItem("Tropicana Orange Juice", 52, 3.99),
    new GroceryItem("Yoplait Low Fat Strawberry Yogurt", 32, 2.99),
    // Bread
    new GroceryItem("El Milagro Corn Tortillas", 10, .66),
    new GroceryItem("El Milagro Flour Tortillas", 12, 1.49),
    new GroceryItem("Bimbo Soft White Bread", 20, 3.29),
    new GroceryItem("Butternut Enriched Bread", 20, 2.49),
    new GroceryItem("Butternut Whole Grain Bread", 20, 2.79),
    new GroceryItem("Brownberry Italian Bread", 22, 2.99),
    new GroceryItem("Thomas Bagels Plain Pre-sliced", 4, 4.79),
    new GroceryItem("Thomas Everything Bagels", 4, 4.79),
    new GroceryItem("Bimbo Pan Blanco", 24, 2.50),
  )

  def choose(numberOfItems: Integer): List[GroceryItem] = {

    var groceryList: List[GroceryItem] = List()
    val rand = new Random()
    for (_ <- 0 until numberOfItems) {
      val index = rand.nextInt(catalog.length - 1)
      groceryList = groceryList.:::(List(catalog(index)))
    }
    groceryList
  }

}
