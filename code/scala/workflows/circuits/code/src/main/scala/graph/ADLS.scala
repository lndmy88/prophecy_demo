package graph

import io.prophecy.libs._
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._
import config.ConfigStore._

object ADLS {

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
                StructField("circuitId",  IntegerType, true),
                StructField("circuitRef", StringType,  true),
                StructField("name",       StringType,  true),
                StructField("location",   StringType,  true),
                StructField("country",    StringType,  true),
                StructField("lat",        DoubleType,  true),
                StructField("lng",        DoubleType,  true),
                StructField("alt",        IntegerType, true),
                StructField("url",        StringType,  true)
              )
            )
          )
          .load("dbfs:/mnt/myformula1dl/raw/circuits.csv")
          .cache()
      case _ =>
        throw new Exception("No valid dataset present to read fabric")
    }
  }

}
