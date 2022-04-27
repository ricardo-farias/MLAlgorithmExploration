import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.sql.DataFrame

object DataVectorizer {

  def vectorize(df: DataFrame): DataFrame = {
    val columns: Array[String] = Array(
      "interior_sqft", "exterior_sqft", "total_sqft",
      "hoa_flag", "bedrooms", "baths", "driveway_flag",
      "garage_flag", "built_date")

    val assembler = new VectorAssembler()
      .setInputCols(columns)
      .setOutputCol("features")

    assembler.transform(df)
  }
}
