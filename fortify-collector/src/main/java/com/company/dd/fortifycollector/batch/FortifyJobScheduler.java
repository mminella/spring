package com.company.dd.fortifycollector.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;


public class FortifyJobScheduler {

	private Job myImportJob;
    private JobLauncher jobLauncher;

    @Autowired
    public FortifyJobScheduler(JobLauncher jobLauncher, @Qualifier("importSSCScanTableJob") Job myImportJob){
        this.myImportJob = myImportJob; 
        this.jobLauncher = jobLauncher;
   }

   @Scheduled(fixedDelay=60000)
   public void runJob(){
       try {
		jobLauncher.run(myImportJob, new JobParameters());
	} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
			| JobParametersInvalidException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
	
}
