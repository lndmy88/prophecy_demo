package graph

import io.prophecy.libs._
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._
import config.ConfigStore._

object Race_results {

  def apply(spark: SparkSession, in: DataFrame): Unit = {
    Config.fabricName match {
      case "dev" =>
        in.write
          .format("delta")
          .option("fileFormat", "parquet")
          .mode("overwrite")
          .saveAsTable("default.race_results")
      case _ =>
        throw new Exception("No valid dataset present to read fabric")
    }
  }

}
