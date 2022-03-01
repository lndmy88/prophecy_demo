package graph

import io.prophecy.libs._
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._
import config.ConfigStore._
import udfs.UDFs._
import udfs._

object Results_Races_Circuits {

  def apply(spark: SparkSession, in0: DataFrame, in1: DataFrame): DataFrame =
    in0
      .as("in0")
      .join(in1.as("in1"),
            col("in1.circuit_id") === col("in0.circuit_id"),
            "inner"
      )
      .select(
        col("in0.result_id").as("result_id"),
        col("in0.race_id").as("race_id"),
        col("in0.driver_id").as("driver_id"),
        col("in0.constructor_id").as("constructor_id"),
        col("in0.grid").as("grid"),
        col("in0.points").as("points"),
        col("in0.fastest_lap").as("fastest_lap"),
        col("in0.race_name").as("race_name"),
        col("in0.race_year").as("race_year"),
        col("in0.circuit_id").as("circuit_id"),
        col("in1.location").as("circuit_location")
      )

}
