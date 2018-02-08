package com.company.dd.fortifycollector.batch;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;

import com.company.dd.fortifycollector.fortify.rdbms.model.Scan;

public class CassandraItemProcessor implements ItemProcessor<Scan, Scan> {

	
	@Override
	public Scan process(Scan s) throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		System.out.println("priniting" +  s.getId());
		Scan cs = new Scan();
		BeanUtils.copyProperties(s,cs);
		//Date test = dateFormat.parse(proj.getCreationDate());
		//System.out.println(" after copying " +cs.getCScanPrimaryKey());
		
		
		return cs;
	}

}
