# Quartz-Scheduler
Stores simple jobs and cron jobs to database. Simple jobs are one time activities scheduled to be done at a particular date in time while 
a Cron job is a scheduled activity that has been specified for a particular day/ day-of-week/ month/ and time. Cron jobs are more detailed 
in setting up the schedule.

## Download instructions
1. Download from repo to your Eclipse IDE
2. Create database - quartz_scheduler_db - to MySql db
  ###### NB: IF USING DIFFERENT DB CHANGE THE spring.jpa.properties.hibernate.dialect PROPERTY IN application.properties TO YOUR SPECIFIC DB ALSO CHANGE org.quartz.jobStore.driverDelegateClass PROPERTY IN quartz.properties
3. Run the schema found in data.sql in your db to create the tables used by the scheduler
  ###### NB: CHANGE DATATYPES TO MATCH YOUR DB
4. Run application
5. Use postman to send requests to application endpoint e.g. to create a cronjob that runs every minute send the following request

![scrren](https://user-images.githubusercontent.com/38307043/38658505-2bbdc79e-3e2e-11e8-8616-2bd7119f9925.png)
