package com.company.dd.fortifycollector.batch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import com.company.dd.fortifycollector.fortify.rdbms.model.Scan;
import com.company.dd.fortifycollector.fortify.rdbms.repository.FortifyScanRepository;

@Component
@StepScope
public class ScanItemReader extends RepositoryItemReader<Scan> implements InitializingBean{

	@Autowired
	private FortifyScanRepository repository;
	private Integer lastScanIdPulled = null;
	
	public ScanItemReader(Integer _lastIdPulled) {
		super();		
		if(_lastIdPulled == null || _lastIdPulled <=0 ){
	    	lastScanIdPulled = 0;
	    } else {
	    	lastScanIdPulled = _lastIdPulled;
	    }
	}

	/*@BeforeStep
	public void initializeValues(StepExecution stepExecution) {
		Integer value = stepExecution.getJobExecution().getExecutionContext().getInt("lastIdPulled");
		System.out.println(">>>>>>>> last_pulled_id = " + value);
	}*/

	@PostConstruct
	protected void setUpRepo() {
		final Map<String, Sort.Direction> sorts = new HashMap<>();
	    sorts.put("id", Direction.ASC);
	    this.setRepository(this.repository);
	    this.setSort(sorts);
	    this.setMethodName("findByScanGreaterThanId"); // You should sepcify the method which  
	               //spring batch should call in your repository to fetch 
	               // data and the arguments it needs needs to be  
	               //specified with the below method.
	    List<Object> methodArgs = new ArrayList<Object>();
	    System.out.println("lastScanIdpulled >>> " + lastScanIdPulled);
	    if(lastScanIdPulled == null || lastScanIdPulled <=0 ){
	    	lastScanIdPulled = 0;
	    }
	    methodArgs.add(lastScanIdPulled);
	    this.setArguments(methodArgs);
	}


}
