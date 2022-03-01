# Standard imports
import json

# Prophecy Imports
from config import Config
from config_loader import load_config
from fabrics import get_spark_job_configuration
from utils.helpers import *
from utils.gems import *

# Airflow imports
from airflow import DAG
from airflow.models import BaseOperator
from airflow.contrib.operators.databricks_operator import DatabricksSubmitRunOperator
from airflow.contrib.operators.emr_add_steps_operator import EmrAddStepsOperator
from airflow.contrib.sensors.emr_step_sensor import EmrStepSensor
from airflow.operators.email_operator import EmailOperator
from airflow.operators.python_operator import BranchPythonOperator, PythonOperator
from airflow.sensors.s3_key_sensor import S3KeySensor
from airflow.providers.apache.livy.operators.livy import LivyOperator

def Sensor_0(config):
    sensor = S3KeySensor(bucket_key = "", wildcard_match = False, aws_conn_id = "", verify = False, soft_fail = False, poke_interval = 60, timeout = 604800, mode = "poke", task_id = "Sensor_0", exponential_backoff = False)
    return sensor

def Workflow_0(config):
    if config.fabric == "dev":
        workflow_id = ""
        workflow_version = "latest"
        workflow_jar = f"{config.pathPrefix}//latest/workflow.jar"
        prophecy_libs_jar = config.prophecyLibsJar
        workflow = DatabricksSubmitRunOperator(task_id = "Workflow_0", new_cluster = get_spark_job_configuration(fabric_name = "dev", job_size = "Small"), spark_jar_task = {"main_class_name": "Main", "parameters": ["-C", "fabricName=" + config.fabric]}, databricks_conn_id = config.connection_id, libraries = [{"jar": workflow_jar}, {"maven": {"coordinates": f"{config.coordinates}", "repo": "https://prophecyio2.jfrog.io/artifactory/sbt-repo"}}])
        return workflow, workflow

config = load_config()
with DAG(**config.dag_args) as dag:
    dag.configuration = config
    op_sensor_0 = Sensor_0(config)
    op_workflow_0 = Workflow_0(config)

    


