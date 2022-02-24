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
      col("raceId").as("race_id"),
      col("year"),
      col("round"),
      col("circuitId").as("circuit_id"),
      col("name"),
      to_timestamp(concat(col("date"), lit(" "), col("time")),
                   "yyyy-MM-dd HH:mm:ss"
      ).as("race_timestamp"),
      current_timestamp().as("ingestion_date")
    )

}
