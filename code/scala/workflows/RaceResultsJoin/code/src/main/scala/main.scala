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
    val df_Constructors = Constructors(spark)
    val df_Circuits     = Circuits(spark)
    val df_Races        = Races(spark)
    val df_Results      = Results(spark)
    val df_Results_Race = Results_Race(spark, df_Races, df_Results)
    val df_Results_Races_Circuits =
      Results_Races_Circuits(spark, df_Results_Race, df_Circuits)
    val df_Drivers = Drivers(spark)
    val df_Results_Drivers =
      Results_Drivers(spark, df_Results_Races_Circuits, df_Drivers)
    val df_Results_Constructors =
      Results_Constructors(spark, df_Results_Drivers, df_Constructors)
    Race_results(spark,           df_Results_Constructors)
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
