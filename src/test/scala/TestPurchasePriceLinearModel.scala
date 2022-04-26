import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.regression.LinearRegressionModel
import org.scalatest.funsuite.AnyFunSuite

class TestPurchasePriceLinearModel extends AnyFunSuite{
  val spark = spark_common.spark

  test("Create model from file") {
    val filename = "models/linear_regression_listing_price_model"
    val purchasePriceLinearModel = new PurchasePriceLinearModel()
    val model = purchasePriceLinearModel.loadModelFromFile(filename)

    assert(model != null)
    assert(model.numFeatures == 9)
  }

  test("Create Model from Scratch") {
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
    val model  = listingModel.createPricingModel(active_df, assembler)

    assert(model != null)
    assert(model.numFeatures == 9)
  }
}
