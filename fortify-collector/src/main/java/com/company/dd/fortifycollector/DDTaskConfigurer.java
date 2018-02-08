package com.company.dd.fortifycollector;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.task.configuration.DefaultTaskConfigurer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DDTaskConfigurer extends DefaultTaskConfigurer{
 
	
	@Autowired
	public DDTaskConfigurer(@Qualifier("taskDataSource") DataSource dataSource) {
		super(dataSource);
	
	}

}
