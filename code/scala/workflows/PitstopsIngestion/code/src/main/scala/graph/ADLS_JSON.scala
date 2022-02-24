package graph

import io.prophecy.libs._
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._
import config.ConfigStore._

object ADLS_JSON {

  def apply(spark: SparkSession): DataFrame = {
    Config.fabricName match {
      case "dev" =>
        spark.read
          .format("json")
          .option("multiLine", true)
          .schema(
            StructType(
              Array(
                StructField("driverId",     LongType,   true),
                StructField("duration",     StringType, true),
                StructField("lap",          LongType,   true),
                StructField("milliseconds", LongType,   true),
                StructField("raceId",       LongType,   true),
                StructField("stop",         LongType,   true),
                StructField("time",         StringType, true)
              )
            )
          )
          .load("dbfs:/mnt/myformula1dl/raw/pit_stops.json")
          .cache()
      case _ =>
        throw new Exception("No valid dataset present to read fabric")
    }
  }

}
