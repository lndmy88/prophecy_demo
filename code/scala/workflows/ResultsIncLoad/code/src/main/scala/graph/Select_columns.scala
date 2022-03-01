package graph

import io.prophecy.libs._
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._
import config.ConfigStore._
import udfs.UDFs._
import udfs._

object Select_columns {

  def apply(spark: SparkSession, in: DataFrame): DataFrame =
    in.select(
      col("resultId").as("result_id"),
      col("raceId").as("race_id"),
      col("driverId").as("driver_id"),
      col("constructorId").as("constructor_id"),
      col("number"),
      col("grid"),
      col("position"),
      col("positionText").as("position_text"),
      col("positionOrder").as("position_order"),
      col("points"),
      col("laps"),
      col("time"),
      col("milliseconds"),
      col("fastestLap").as("fastest_lap"),
      col("rank"),
      col("fastestLapTime").as("fastest_lap_time"),
      col("fastestLapSpeed").as("fastest_lap_speed"),
      current_timestamp().as("ingestion_date")
    )

}
