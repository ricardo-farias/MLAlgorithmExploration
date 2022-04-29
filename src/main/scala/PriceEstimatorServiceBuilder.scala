import org.apache.spark.ml.regression.LinearRegression
import org.apache.spark.ml.regression.LinearRegressionModel
import org.apache.spark.sql.{Dataset, Row}


class PriceEstimatorServiceBuilder() {
  def loadModelFromFile(filePath: String) : PriceEstimatorService = {
    val model = LinearRegressionModel.load(filePath)
    new PriceEstimatorService(model)
  }

  def createPricingModel(df: Dataset[Row]): PriceEstimatorService = {

    val outputDF = DataVectorizer.vectorize(df)
    val split: Array[Dataset[Row]] = outputDF.randomSplit(Array(.7, .3))
    val train = split.apply(0)

    val algo = new LinearRegression()
      .setFeaturesCol("features")
      .setLabelCol("sold_price")

    val model = algo.fit(train)
    new PriceEstimatorService(model)
  }


}
