package graph

import io.prophecy.libs._
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._
import config.ConfigStore._
import udfs.UDFs._
import udfs._

object Transform {

  def apply(spark: SparkSession, in: DataFrame): DataFrame =
    in.select(
      col("circuitId").as("circuit_id"),
      col("circuitRef").as("circuit_ref"),
      col("name"),
      col("location"),
      col("country"),
      col("lat").as("latitude"),
      col("lng").as("longitude"),
      col("alt").as("altitude")
    )

}
