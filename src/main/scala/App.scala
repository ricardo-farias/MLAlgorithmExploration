import GrocerySelector.GrocerySelector
import PriceEstimator.PriceEstimatorServiceBuilder
import org.apache.spark.sql.Row
import org.apache.spark.sql.types.{IntegerType, StructField, StructType}

object App {

  def main(args: Array[String]): Unit = {
//    val filename = "models/linear_regression_listing_price_model"
//    val spark = spark_common.spark
//
//    val priceEstimatorService = new PriceEstimatorServiceBuilder().loadModelFromFile(filename)
//
//    val schema: StructType = new StructType(Array(
//      StructField("interior_sqft", IntegerType),
//      StructField("exterior_sqft", IntegerType),
//      StructField("total_sqft", IntegerType),
//      StructField("hoa_flag", IntegerType),
//      StructField("bedrooms", IntegerType),
//      StructField("baths", IntegerType),
//      StructField("driveway_flag", IntegerType),
//      StructField("garage_flag", IntegerType),
//      StructField("built_date", IntegerType),
//      StructField("sold_price", IntegerType)
//    ))
//
//    val data = List(1728, 5227, 6955, 0, 3, 2, 1, 1, 1962, 189000)
//    val rdd = spark.sparkContext.parallelize(Seq(data)).map(v => Row(v: _*))
//    val df = spark.createDataFrame(rdd, schema)
//
//    priceEstimatorService.generatePurchasePrice(df)

    val grocerySelector = new GrocerySelector(1, 20)

    grocerySelector.run()

  }
}
