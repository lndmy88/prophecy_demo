package graph

import io.prophecy.libs._
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._
import config.ConfigStore._
import udfs.UDFs._
import udfs._

object Results_Race {

  def apply(spark: SparkSession, in0: DataFrame, in1: DataFrame): DataFrame =
    in0
      .as("in0")
      .join(in1.as("in1"), col("in1.race_id") === col("in0.race_id"), "inner")
      .select(
        col("in1.result_id").as("result_id"),
        col("in1.race_id").as("race_id"),
        col("in1.driver_id").as("driver_id"),
        col("in1.constructor_id").as("constructor_id"),
        col("in1.grid").as("grid"),
        col("in1.points").as("points"),
        col("in1.fastest_lap").as("fastest_lap"),
        col("in0.name").as("race_name"),
        col("in0.year").as("race_year"),
        col("in0.date").as("race_date"),
        col("in0.circuit_id").as("circuit_id")
      )

}
