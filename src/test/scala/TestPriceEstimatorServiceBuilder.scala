import PriceEstimator.PriceEstimatorServiceBuilder
import org.scalatest.funsuite.AnyFunSuite

class TestPriceEstimatorServiceBuilder extends AnyFunSuite {
  private val spark = spark_common.spark

  test("Create model from file") {
    val filename = "models/linear_regression_listing_price_model"
    val purchasePriceLinearModel = new PriceEstimatorServiceBuilder()
    val model = purchasePriceLinearModel.loadModelFromFile(filename).getModel

    assert(model != null)
    assert(model.numFeatures == 9)
  }

  test("Create Model from Scratch") {
    val active_df = spark
      .read
      .option("header", "true")
      .option("inferSchema", "true")
      .csv("./resources/SoldHouses.csv")

    val listingModel : PriceEstimatorServiceBuilder = new PriceEstimatorServiceBuilder()
    val model  = listingModel.createPricingModel(active_df).getModel

    assert(model != null)
    assert(model.numFeatures == 9)
  }
}
