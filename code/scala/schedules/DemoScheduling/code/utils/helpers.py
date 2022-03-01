
# Put your helpers in this file
# Helpers == Utility function
# As an Example, a utility to alert over slack for failures:
#
# This can be used as a value for on_failure_callback argument for tasks.
#
# def alert_slack(context):
#     # Here context is TaskContext
#     slack_message = construct_message_from_context(context)
#     creds = load_slack_creds()
#     requests.post(...)
#

from airflow.utils import timezone
from datetime import timedelta

#############################################################################
####################### Prophecy Provided Functions #########################

# Below you have simple string parsers provided by prophecy.
# DO NOT DELETE THEM. Although you can edit them, it's NOT RECOMMENDED!

def parse_datetime(s):
    return timezone.parse(*s.split())

def parse_duration(s):
    amount, unit = s.split()
    kwargs = {unit: int(amount)}
    return timedelta(**kwargs)


#############################################################################
###################### Put Your Helper functions Below ######################

