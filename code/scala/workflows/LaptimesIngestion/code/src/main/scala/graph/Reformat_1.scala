package graph

import io.prophecy.libs._
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._
import config.ConfigStore._
import udfs.UDFs._
import udfs._

object Reformat_1 {

  def apply(spark: SparkSession, in: DataFrame): DataFrame =
    in.select(col("race_id"),
              col("driver_id"),
              col("lap"),
              col("position"),
              col("time"),
              col("milliseconds"),
              current_timestamp().as("ingestion_date")
    )

}
