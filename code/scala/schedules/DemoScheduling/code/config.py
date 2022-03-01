from datetime import datetime, timedelta
import sys
import os
sys.path.insert(0, os.path.abspath(os.path.dirname(__file__)))
from utils.helpers import *


class Config(object):
    def __init__(self, fabric: str):
        self.fabric = fabric
        # Dag Args are passed to the DAG constructor as keyword arguments
        self.dag_args = {'default_args' : {}}
        # default_args are the common parameters for all the operators of the dag
        self.default_args = self.dag_args['default_args']
        self.dag_args['dag_id'] = "DemoScheduling"
        self.dag_args['start_date'] = parse_datetime("2022-02-28T02:06:13.511404 GMT")
        self.dag_args['schedule_interval'] = "0 0 * * *"
