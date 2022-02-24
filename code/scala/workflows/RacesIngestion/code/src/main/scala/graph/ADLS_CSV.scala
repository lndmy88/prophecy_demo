package graph

import io.prophecy.libs._
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._
import config.ConfigStore._

object ADLS_CSV {

  def apply(spark: SparkSession): DataFrame = {
    Config.fabricName match {
      case "dev" =>
        spark.read
          .format("csv")
          .option("header", true)
          .option("sep",    ",".stripPrefix("\\"))
          .schema(
            StructType(
              Array(
                StructField("raceId",    IntegerType, true),
                StructField("year",      IntegerType, true),
                StructField("round",     IntegerType, true),
                StructField("circuitId", IntegerType, true),
                StructField("name",      StringType,  true),
                StructField("date",      StringType,  true),
                StructField("time",      StringType,  true),
                StructField("url",       StringType,  true)
              )
            )
          )
          .load("dbfs:/mnt/myformula1dl/raw/races.csv")
          .cache()
      case _ =>
        throw new Exception("No valid dataset present to read fabric")
    }
  }

}
