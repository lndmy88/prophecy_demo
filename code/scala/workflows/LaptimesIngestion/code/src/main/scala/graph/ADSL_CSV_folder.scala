package graph

import io.prophecy.libs._
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._
import config.ConfigStore._

object ADSL_CSV_folder {

  def apply(spark: SparkSession): DataFrame = {
    Config.fabricName match {
      case "dev" =>
        spark.read
          .format("csv")
          .option("header", false)
          .option("sep",    ",".stripPrefix("\\"))
          .schema(
            StructType(
              Array(
                StructField("race_id",      IntegerType, true),
                StructField("driver_id",    IntegerType, true),
                StructField("lap",          IntegerType, true),
                StructField("position",     IntegerType, true),
                StructField("time",         StringType,  true),
                StructField("milliseconds", IntegerType, true)
              )
            )
          )
          .load("dbfs:/mnt/myformula1dl/raw/lap_times/")
          .cache()
      case _ =>
        throw new Exception("No valid dataset present to read fabric")
    }
  }

}
