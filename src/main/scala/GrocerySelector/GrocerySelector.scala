package GrocerySelector

import scala.util.Random

class GrocerySelector(numberOfIterations: Integer, populationSize: Integer) {

  // Creates a population of GroceryAssistants
  // All GroceryAssistants get money to buy GroceriesItems

  private val money: Double = 20.00
  private val crossoverPercentage: Double = .75
  private val mutationRate: Double = .25
  private var pop: List[GroceryAssistant] = List()

  private def generatePopulation(): Unit ={
    for (_ <- 1 to populationSize){
      pop = pop.::(
        new GroceryAssistant(money).randomSelection()
      )
    }
  }

  private def reproduce(): Unit = {
    // TODO:

    // random split of cart by n
    // swap ga1 and ga2's cart splits
    // combine and recreate both
    // add them to new population pool
    var tempPopulation: List[GroceryAssistant] = List()
    val rand = new Random()
    val successPop = pop.filter(a => a.fitness() > 0).sortBy(a => a.fitness())

    // Selection of mates - Note the same assistant might be chosen twice :(
    val ga1 = successPop(rand.nextInt(successPop.length-1))
    val ga2 = successPop(rand.nextInt(successPop.length-1))

    // Remove Assistant 1 and 2 from the population
    tempPopulation = successPop.filter(a => a.ne(ga1) & a.ne(ga2))

    // Gene Splice
    val cartSize = ga1.shoppingCart.length-1
    val spilt = rand.nextInt(cartSize)

    val ga1Splice = ga1.shoppingCart.drop(spilt)
    val ga2Splice = ga2.shoppingCart.drop(spilt)

    //Remove spliced genes from assistants
    ga1.shoppingCart = ga1.shoppingCart.splitAt(spilt)._1
    ga2.shoppingCart = ga2.shoppingCart.splitAt(spilt)._1

    // Swap Genes
    ga1.shoppingCart = ga1.shoppingCart.:::(ga2Splice)
    ga2.shoppingCart = ga2.shoppingCart.:::(ga1Splice)

    // Mutations
    val prob: Double = rand.nextInt(100)/100
    if (prob > .75){
      val pos = rand.nextInt(cartSize)
      ga1.mutate(pos, Catalog.choose(1).head)
      ga2.mutate(pos, Catalog.choose(1).head)
    }

    tempPopulation = tempPopulation.:::(List(ga1, ga2))

    pop = tempPopulation
  }

  def run(): Unit = {
    generatePopulation()
    //println(pop)
    var iterations = 0
    while (iterations < numberOfIterations){
      reproduce() // cross-over and mutation will happen in this reproduction step
      generatePopulation()
      println(pop.length)

      iterations += 1
    }
  }

  //  1) Randomly initialize populations p
  //  2) Determine fitness of population
  //  3) Until convergence repeat:
  //      a) Select parents from population
  //      b) Crossover and generate new population
  //      c) Perform mutation on new population
  //      d) Calculate fitness for new population

}
