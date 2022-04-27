import org.apache.spark.sql.{DataFrame, Row}
import org.apache.spark.sql.functions.{abs, col}
import org.apache.spark.sql.types.{DoubleType, IntegerType, StructField, StructType}
import org.scalatest.funsuite.AnyFunSuite

class TestPriceEstimatorService extends AnyFunSuite {

  private val filename = "models/linear_regression_listing_price_model"
  private val spark = spark_common.spark

  test("Generate purchase price on a dataframe containing a single row should return a " +
    "predicted value that has less than a 50% error") {
    val priceEstimatorService = new PriceEstimatorServiceBuilder().loadModelFromFile(filename)
    val schema: StructType = new StructType(Array(
      StructField("interior_sqft", IntegerType),
      StructField("exterior_sqft", IntegerType),
      StructField("total_sqft", IntegerType),
      StructField("hoa_flag", IntegerType),
      StructField("bedrooms", IntegerType),
      StructField("baths", IntegerType),
      StructField("driveway_flag", IntegerType),
      StructField("garage_flag", IntegerType),
      StructField("built_date", IntegerType),
      StructField("sold_price", IntegerType)
    ))
    val data = List(1728,5227,6955,0,3,2,1,1,1962,189000)
    val rdd = spark.sparkContext.parallelize(Seq(data)).map(v => Row(v: _*))
    val df = spark.createDataFrame(rdd, schema)

    val prediction = priceEstimatorService.generatePurchasePrice(df)
      .withColumn("percent_error",
        (abs(col("prediction") - col("sold_price"))/col("sold_price")) * 100)
    val error = prediction.select("percent_error").collect()(0).getDouble(0)

    assert(prediction != null)
    assert(prediction.count() == 1)
    assert(prediction.columns.length == 13)
    assert(error < 50)
  }

  test("Generate purchase price on a dataframe containing a single row without sold_price " +
    "should return a predicted value") {
    val priceEstimatorService = new PriceEstimatorServiceBuilder().loadModelFromFile(filename)
    val schema: StructType = new StructType(Array(
      StructField("interior_sqft", IntegerType),
      StructField("exterior_sqft", IntegerType),
      StructField("total_sqft", IntegerType),
      StructField("hoa_flag", IntegerType),
      StructField("bedrooms", IntegerType),
      StructField("baths", IntegerType),
      StructField("driveway_flag", IntegerType),
      StructField("garage_flag", IntegerType),
      StructField("built_date", IntegerType)
    ))
    val data = List(1088,0,1088,1,2,2,1,1,2000)
    val rdd = spark.sparkContext.parallelize(Seq(data)).map(v => Row(v: _*))
    val df = spark.createDataFrame(rdd, schema)

    val prediction = priceEstimatorService.generatePurchasePrice(df)

    prediction.show()
    assert(prediction != null)
    assert(prediction.count() == 1)
    assert(prediction.columns.length == 12)
  }



}
