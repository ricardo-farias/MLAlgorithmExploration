import org.apache.spark.sql.SparkSession

object spark_common {

  val spark = {
    if (SparkSession.getActiveSession.isEmpty) {
      val spark = SparkSession.builder().appName("app")
        .master("local")
        .getOrCreate()
      spark
    } else {
      val spark = SparkSession.builder().getOrCreate()
      spark
    }
  }
}
