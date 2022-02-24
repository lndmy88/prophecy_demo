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
                StructField("code",      StringType, true),
                StructField("dob",       StringType, true),
                StructField("driverId",  LongType,   true),
                StructField("driverRef", StringType, true),
                StructField("name",
                            StructType(
                              Array(StructField("forename", StringType, true),
                                    StructField("surname",  StringType, true)
                              )
                            ),
                            true
                ),
                StructField("nationality", StringType, true),
                StructField("number",      StringType, true)
              )
            )
          )
          .load("dbfs:/mnt/myformula1dl/raw/drivers.json")
          .cache()
      case _ =>
        throw new Exception("No valid dataset present to read fabric")
    }
  }

}
