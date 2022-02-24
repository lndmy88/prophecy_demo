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
    val df_ADLS_JSON_folder = ADLS_JSON_folder(spark)
    val df_Transform        = Transform(spark, df_ADLS_JSON_folder)
    Table_in_the_cluster(spark, df_Transform)
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
