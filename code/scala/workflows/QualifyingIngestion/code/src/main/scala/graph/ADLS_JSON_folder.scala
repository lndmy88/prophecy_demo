package graph

import io.prophecy.libs._
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._
import config.ConfigStore._

object ADLS_JSON_folder {

  def apply(spark: SparkSession): DataFrame = {
    Config.fabricName match {
      case "dev" =>
        spark.read
          .format("json")
          .option("multiLine", true)
          .schema(
            StructType(
              Array(
                StructField("constructorId", LongType,   true),
                StructField("driverId",      LongType,   true),
                StructField("number",        LongType,   true),
                StructField("position",      LongType,   true),
                StructField("q1",            StringType, true),
                StructField("q2",            StringType, true),
                StructField("q3",            StringType, true),
                StructField("qualifyId",     LongType,   true),
                StructField("raceId",        LongType,   true)
              )
            )
          )
          .load("dbfs:/mnt/myformula1dl/raw/qualifying/")
          .cache()
      case _ =>
        throw new Exception("No valid dataset present to read fabric")
    }
  }

}
