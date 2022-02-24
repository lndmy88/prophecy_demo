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
      col("driverId").as("driver_id"),
      col("driverRef").as("driver_ref"),
      col("number"),
      col("code"),
      concat(col("name.forename"), lit(" "), col("name.surname")).as("name"),
      col("dob"),
      col("nationality"),
      current_timestamp().as("ingestion_date")
    )

}
