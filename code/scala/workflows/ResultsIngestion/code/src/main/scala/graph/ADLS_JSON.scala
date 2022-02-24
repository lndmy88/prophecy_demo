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
          .schema(
            StructType(
              Array(
                StructField("constructorId",   LongType,   true),
                StructField("driverId",        LongType,   true),
                StructField("fastestLap",      StringType, true),
                StructField("fastestLapSpeed", StringType, true),
                StructField("fastestLapTime",  StringType, true),
                StructField("grid",            LongType,   true),
                StructField("laps",            LongType,   true),
                StructField("milliseconds",    StringType, true),
                StructField("number",          StringType, true),
                StructField("points",          DoubleType, true),
                StructField("position",        StringType, true),
                StructField("positionOrder",   LongType,   true),
                StructField("positionText",    StringType, true),
                StructField("raceId",          LongType,   true),
                StructField("rank",            StringType, true),
                StructField("resultId",        LongType,   true),
                StructField("statusId",        LongType,   true),
                StructField("time",            StringType, true)
              )
            )
          )
          .load("dbfs:/mnt/myformula1dl/raw/results.json")
          .cache()
      case _ =>
        throw new Exception("No valid dataset present to read fabric")
    }
  }

}
