import org.apache.spark.ml.feature.{Normalizer, VectorAssembler}

object Main {

  def main(args: Array[String]): Unit = {
    val active_df = spark_common.spark
      .read
      .option("header", "true")
      .option("inferSchema", "true")
      .csv("./resources/SoldHouses.csv")

    val columns: Array[String] = Array("interior_sqft", "exterior_sqft", "total_sqft", "hoa_flag", "bedrooms", "baths",
      "driveway_flag", "garage_flag", "built_date")

    val assembler = new VectorAssembler()
      .setInputCols(columns)
      .setOutputCol("features")

    val listingModel : PurchasePriceLinearModel = new PurchasePriceLinearModel()
    val listingPriceModel  = listingModel.createPricingModel(active_df, assembler)
    val summary = listingPriceModel.summary

    val vectorizedDF = assembler.transform(active_df)
    val predictions = listingPriceModel.transform(vectorizedDF)
    predictions.show()

    println("-------- Summary --------")
    println(s"Degrees of freedom: \n${summary.degreesOfFreedom}")
    println(s"Explained Variance: \n${summary.explainedVariance}")
    println(s"r squared score: \n${summary.r2}")
    println(s"r squared adj score: \n${summary.r2adj}")
    println(s"Objective History: \n${summary.objectiveHistory.mkString(",")}")

    //    listingPriceModel.save("models/linear_regression_listing_price_model")
  }
}
