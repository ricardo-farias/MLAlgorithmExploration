import org.apache.spark.ml.regression.LinearRegressionModel
import org.apache.spark.sql.DataFrame

class PriceEstimatorService(model: LinearRegressionModel) {

  def getModel: LinearRegressionModel = model


  def saveModel() : Unit = {
    model.write.overwrite.save("models/linear_regression_listing_price_model")
  }

  def generatePurchasePrice(df: DataFrame): DataFrame = {
    val vectorizedDataFrame = DataVectorizer.vectorize(df)
    val results: DataFrame = model.transform(vectorizedDataFrame)

    results
  }

}
