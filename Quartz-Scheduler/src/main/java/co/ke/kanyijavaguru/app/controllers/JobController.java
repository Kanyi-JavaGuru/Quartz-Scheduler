package co.ke.kanyijavaguru.app.controllers;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.ke.kanyijavaguru.app.jobs.CronJob;
import co.ke.kanyijavaguru.app.jobs.SimpleJob;
import co.ke.kanyijavaguru.app.services.JobService;

@RestController
public class JobController {

	@Autowired
	@Lazy
	JobService jobService;

	@RequestMapping("schedule")	
	public void schedule(@RequestParam("jobName") String jobName, 
			@RequestParam("jobScheduleTime") @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm") Date jobScheduleTime, 
			@RequestParam("cronExpression") String cronExpression){
		System.out.println("JobController.schedule()");

		//Job Name is mandatory
		if(jobName == null || jobName.trim().equals("")){
		}

		//Check if job Name is unique;
		if(!jobService.isJobWithNamePresent(jobName)){

			if(cronExpression == null || cronExpression.trim().equals("")){
				//Single Trigger
				boolean status = jobService.scheduleOneTimeJob(jobName, SimpleJob.class, jobScheduleTime);
				
			}else{
				//Cron Trigger
				boolean status = jobService.scheduleCronJob(jobName, CronJob.class, jobScheduleTime, cronExpression);
								
			}
		}
	}

	@RequestMapping("unschedule")
	public void unschedule(@RequestParam("jobName") String jobName) {
		System.out.println("JobController.unschedule()");
		jobService.unScheduleJob(jobName);
	}

	@RequestMapping("delete")
	public void delete(@RequestParam("jobName") String jobName) {
		System.out.println("JobController.delete()");		

		if(jobService.isJobWithNamePresent(jobName)){
			boolean isJobRunning = jobService.isJobRunning(jobName);

			if(!isJobRunning){
				boolean status = jobService.deleteJob(jobName);
			}
		}
	}

	@RequestMapping("pause")
	public void pause(@RequestParam("jobName") String jobName) {
		System.out.println("JobController.pause()");

		if(jobService.isJobWithNamePresent(jobName)){

			boolean isJobRunning = jobService.isJobRunning(jobName);

			if(!isJobRunning) {
				boolean status = jobService.pauseJob(jobName);
			}
		}
	}

	@RequestMapping("resume")
	public void resume(@RequestParam("jobName") String jobName) {
		System.out.println("JobController.resume()");

		if(jobService.isJobWithNamePresent(jobName)){
			String jobState = jobService.getJobState(jobName);

			if(jobState.equals("PAUSED")){
				System.out.println("Job current state is PAUSED, Resuming job...");
				boolean status = jobService.resumeJob(jobName);
			}
		}
	}

	@RequestMapping("update")
	public void updateJob(@RequestParam("jobName") String jobName, 
			@RequestParam("jobScheduleTime") @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm") Date jobScheduleTime, 
			@RequestParam("cronExpression") String cronExpression){
		System.out.println("JobController.updateJob()");

		//Job Name is mandatory
		if(jobName == null || jobName.trim().equals("")){
		}

		//Edit Job
		if(jobService.isJobWithNamePresent(jobName)){
			
			if(cronExpression == null || cronExpression.trim().equals("")){
				//Single Trigger
				boolean status = jobService.updateOneTimeJob(jobName, jobScheduleTime);				
			}else{
				//Cron Trigger
				boolean status = jobService.updateCronJob(jobName, jobScheduleTime, cronExpression);								
			}
		}
	}

	@RequestMapping("jobs")
	public void getAllJobs(){
		System.out.println("JobController.getAllJobs()");
		List<Map<String, Object>> list = jobService.getAllJobs();
	}

	@RequestMapping("checkJobName")
	public void checkJobName(@RequestParam("jobName") String jobName){
		System.out.println("JobController.checkJobName()");
		//Job Name is mandatory
		if(jobName == null || jobName.trim().equals("")){
		}		
		boolean status = jobService.isJobWithNamePresent(jobName);
	}

	@RequestMapping("isJobRunning")
	public void isJobRunning(@RequestParam("jobName") String jobName) {
		System.out.println("JobController.isJobRunning()");

		boolean status = jobService.isJobRunning(jobName);
	}

	@RequestMapping("jobState")
	public void getJobState(@RequestParam("jobName") String jobName) {
		System.out.println("JobController.getJobState()");

		String jobState = jobService.getJobState(jobName);
	}

	@RequestMapping("stop")
	public void stopJob(@RequestParam("jobName") String jobName) {
		System.out.println("JobController.stopJob()");

		if(jobService.isJobWithNamePresent(jobName)){

			if(jobService.isJobRunning(jobName)){
				boolean status = jobService.stopJob(jobName);
			}
		}
	}

	@RequestMapping("start")
	public void startJobNow(@RequestParam("jobName") String jobName) {
		System.out.println("JobController.startJobNow()");

		if(jobService.isJobWithNamePresent(jobName)){

			if(!jobService.isJobRunning(jobName)){
				boolean status = jobService.startJobNow(jobName);
			}
		}
	}

}
