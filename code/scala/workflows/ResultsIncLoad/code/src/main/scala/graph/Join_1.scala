package graph

import io.prophecy.libs._
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._
import config.ConfigStore._
import udfs.UDFs._
import udfs._

object Join_1 {

  def apply(spark: SparkSession, in0: DataFrame, in1: DataFrame): DataFrame =
    in0
      .as("in0")
      .join(in1.as("in1"),
            col("in1.resultId") === col("in0.result_id"),
            "right_outer"
      )
      .select(
        col("in1.constructorId").as("constructorId"),
        col("in1.driverId").as("driverId"),
        col("in1.fastestLap").as("fastestLap"),
        col("in1.fastestLapSpeed").as("fastestLapSpeed"),
        col("in1.fastestLapTime").as("fastestLapTime"),
        col("in1.grid").as("grid"),
        col("in1.laps").as("laps"),
        col("in1.milliseconds").as("milliseconds"),
        col("in1.number").as("number"),
        col("in1.points").as("points"),
        col("in1.position").as("position"),
        col("in1.positionOrder").as("positionOrder"),
        col("in1.positionText").as("positionText"),
        col("in1.raceId").as("raceId"),
        col("in1.rank").as("rank"),
        col("in1.resultId").as("resultId"),
        col("in1.statusId").as("statusId"),
        col("in1.time").as("time"),
        col("in0.result_id").as("current_result_id")
      )

}
