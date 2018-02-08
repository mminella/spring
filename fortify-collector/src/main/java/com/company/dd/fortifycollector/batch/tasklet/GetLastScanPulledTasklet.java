package com.company.dd.fortifycollector.batch.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Component;

@Component
@JobScope
public class GetLastScanPulledTasklet implements Tasklet{
	
	@Autowired
	private CassandraOperations csOps;
	
	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext chunkCx) throws Exception {
		String selectQuery = "select max(id) as maxId from fortify_ssc_scan";		
		Integer lastIdPulled = csOps.selectOne(selectQuery, Integer.class);
		System.out.println("last_pulled_id");
		if(lastIdPulled == null){
			lastIdPulled = 0;
		}
		
		chunkCx
	        .getStepContext()
	        .getStepExecution()
	        .getJobExecution()
	        .getExecutionContext()
	        .put("lastIdPulled", lastIdPulled);
		return  RepeatStatus.FINISHED;
	}

}
