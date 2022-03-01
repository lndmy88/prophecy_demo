
#### functions containing all available job types

def get_spark_job_configuration(fabric_name:str, job_size:str):
  job_types = []

  job_types.append({'fabric':'dev', 'size': 'Small','spark_version': '8.2.x-scala2.12','node_type_id':'Standard_DS3_v2','num_workers':'1'})

  for job in job_types:
   if(job['fabric'] == fabric_name and job['size'] == job_size):
     del job['fabric']
     del job['size']
     return job


