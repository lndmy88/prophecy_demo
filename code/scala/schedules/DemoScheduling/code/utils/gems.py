
from airflow.operators.dummy_operator import DummyOperator
from airflow.operators.bash_operator import BashOperator

# Put your gems in this file.
# Gem == a function that returns an operator

# NOTE:
#     a) Your gems in this file should take task_id as the first argument.
#     b) You need to pass that task_id to the operator class as a keyword argument

# See examples below

def dummy(task_id):
    return DummyOperator(task_id = task_id)

def bash(task_id, bash_command: str):
    return BashOperator(task_id = task_id, bash_command = bash_command)

