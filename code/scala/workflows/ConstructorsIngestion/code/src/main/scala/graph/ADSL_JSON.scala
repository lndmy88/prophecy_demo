package graph

import io.prophecy.libs._
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._
import config.ConfigStore._

object ADSL_JSON {

  def apply(spark: SparkSession): DataFrame = {
    Config.fabricName match {
      case "dev" =>
        spark.read
          .format("json")
          .schema(
            StructType(
              Array(
                StructField("constructorId",  LongType,   true),
                StructField("constructorRef", StringType, true),
                StructField("name",           StringType, true),
                StructField("nationality",    StringType, true),
                StructField("url",            StringType, true)
              )
            )
          )
          .load("dbfs:/mnt/myformula1dl/raw/constructors.json")
          .cache()
      case _ =>
        throw new Exception("No valid dataset present to read fabric")
    }
  }

}
