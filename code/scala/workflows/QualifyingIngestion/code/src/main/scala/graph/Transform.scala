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
      col("qualifyId"),
      col("raceId"),
      col("driverId"),
      col("constructorId"),
      col("number"),
      col("position"),
      col("q1"),
      col("q2"),
      col("q3"),
      current_timestamp().as("ingestion_date")
    )

}
