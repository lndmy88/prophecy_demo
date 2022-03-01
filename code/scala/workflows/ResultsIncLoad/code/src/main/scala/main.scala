import io.prophecy.libs._
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._
import config.ConfigStore._
import udfs.UDFs._
import udfs._
import graph._

object Main {

  def apply(spark: SparkSession): Unit = {
    val df_Current_data      = Current_data(spark)
    val df_JSON_source_files = JSON_source_files(spark)
    val df_Join_1            = Join_1(spark,         df_Current_data, df_JSON_source_files)
    val df_Delta_data        = Delta_data(spark,     df_Join_1)
    val df_Select_columns    = Select_columns(spark, df_Delta_data)
    Table_in_the_cluster(spark, df_Select_columns)
  }

  def main(args: Array[String]): Unit = {
    import config._
    ConfigStore.Config = ConfigurationFactoryImpl.fromCLI(args)
    val spark: SparkSession = SparkSession
      .builder()
      .appName("Prophecy Workflow")
      .config("spark.default.parallelism", "4")
      .enableHiveSupport()
      .getOrCreate()
    apply(spark)
  }

}
