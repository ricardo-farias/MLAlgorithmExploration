import org.apache.spark.ml.feature.{VectorAssembler, Normalizer}
import org.apache.spark.ml.regression.LinearRegression
import org.apache.spark.ml.regression.LinearRegressionModel
import org.apache.spark.sql.{Dataset, Row}


class PurchasePriceLinearModel {

  def loadModelFromFile(filePath: String) : LinearRegressionModel = {
    val model = LinearRegressionModel.load(filePath)
    model
  }

  def createPricingModel(df: Dataset[Row], assembler: VectorAssembler): LinearRegressionModel = {

    val outputDF = assembler.transform(df)
    val split: Array[Dataset[Row]] = outputDF.randomSplit(Array(.7, .3))
    val train = split.apply(0)

    val algo = new LinearRegression()
      .setFeaturesCol("features")
      .setLabelCol("sold_price")

    val model = algo.fit(train)
    model
  }


}
