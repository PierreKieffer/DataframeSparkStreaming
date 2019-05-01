
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.types._
import utils.AppConfiguration

object StreamProcessor {
  def main(args: Array[String]) {

    if (args.length != 1){
      throw new IllegalArgumentException("Path to config file")
    }

    // Load config.yml file
    AppConfiguration.initializeAppConfig(args(0))
    val inputPath = AppConfiguration.inputPath
    val schemaPath = AppConfiguration.schemaPath
    val format = AppConfiguration.fileFormat

    val spark = SparkSession.builder().master("local[*]").appName("DataFrameStreamProcessor").getOrCreate()

    // Load schema
    val jsonSchemaFile = spark.read.textFile(schemaPath).take(1)(0)
    val customSchema = DataType.fromJson(jsonSchemaFile).asInstanceOf[StructType]

    if (format == "parquet") {
      val path = inputPath + "/*.parquet"
      val dataFrame = spark.readStream.schema(customSchema).option("header","true").parquet(path)
      val preprocessedData = dataPreprocessor(dataFrame)
      val query = preprocessedData.writeStream.outputMode("append").format("console").start()
      query.awaitTermination()

    } else if (format == "csv") {
      val path = inputPath + "/*.csv"
      val dataFrame = spark.readStream.format("csv").option("header","true").schema(customSchema).load(path)
      val preprocessedData = dataPreprocessor(dataFrame)
      val query = preprocessedData.writeStream.outputMode("append").format("console").start()
      query.awaitTermination()
    }
  }

  def dataPreprocessor(inputDataFrame : DataFrame) : DataFrame = {

    // process inputDataFrame
    // ...

    return inputDataFrame

  }

}



