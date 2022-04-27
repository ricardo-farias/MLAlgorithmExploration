import org.apache.spark.ml.feature.{Normalizer, VectorAssembler}

object App {

  def main(args: Array[String]): Unit = {
    val active_df = spark_common.spark
      .read
      .option("header", "true")
      .option("inferSchema", "true")
      .csv("./resources/SoldHouses.csv")

    val listingModel : PriceEstimatorServiceBuilder = new PriceEstimatorServiceBuilder()
    val priceEstimatorService  = listingModel.createPricingModel(active_df)
    val listingPriceModel = priceEstimatorService.getModel()
    val summary = listingPriceModel.summary

    val predictions = priceEstimatorService.generatePurchasePrice(active_df)
    predictions.show()

    println("-------- Summary --------")
    println(s"Degrees of freedom: \n${summary.degreesOfFreedom}")
    println(s"Explained Variance: \n${summary.explainedVariance}")
    println(s"r squared score: \n${summary.r2}")
    println(s"r squared adj score: \n${summary.r2adj}")
    println(s"Objective History: \n${summary.objectiveHistory.mkString(",")}")

    listingPriceModel.write.overwrite.save("models/linear_regression_listing_price_model")
  }
}
