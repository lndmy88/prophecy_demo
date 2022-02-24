package graph

import io.prophecy.libs._
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._
import config.ConfigStore._
import udfs.UDFs._
import udfs._

object Rename_the_columns {

  def apply(spark: SparkSession, in: DataFrame): DataFrame =
    in.select(col("constructorId").as("constructor_id"),
              col("constructorRef").as("constructor_ref"),
              col("name"),
              col("nationality")
    )

}
