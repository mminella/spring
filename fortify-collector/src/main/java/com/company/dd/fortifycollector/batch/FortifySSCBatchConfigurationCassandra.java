package com.company.dd.fortifycollector.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.MapJobExplorerFactoryBean;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.company.dd.fortifycollector.batch.tasklet.GetLastScanPulledTasklet;
import com.company.dd.fortifycollector.fortify.rdbms.model.Scan;


@Configuration
@EnableBatchProcessing
public class FortifySSCBatchConfigurationCassandra {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;
  
    //@Autowired
    //public CassandraBatchItemWriter itemWriter;
    
    @Autowired
    public GetLastScanPulledTasklet tasklet;
    
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new ResourcelessTransactionManager();
    }


    @Bean
    public JobExplorer jobExplorer() throws Exception {
        MapJobExplorerFactoryBean jobExplorerFactory = new MapJobExplorerFactoryBean(mapJobRepositoryFactoryBean());
        jobExplorerFactory.afterPropertiesSet();
        return jobExplorerFactory.getObject();
    }

    @Bean
    public MapJobRepositoryFactoryBean mapJobRepositoryFactoryBean() {
        MapJobRepositoryFactoryBean mapJobRepositoryFactoryBean = new MapJobRepositoryFactoryBean();
        mapJobRepositoryFactoryBean.setTransactionManager(transactionManager());
        return mapJobRepositoryFactoryBean;
    }

    @Bean
    public JobRepository jobRepository() throws Exception {
        return mapJobRepositoryFactoryBean().getObject();
    }

    @Bean
    public JobLauncher jobLauncher() throws Exception {
        SimpleJobLauncher simpleJobLauncher = new SimpleJobLauncher();
        simpleJobLauncher.setJobRepository(jobRepository());
        return simpleJobLauncher;
    }
    
    @Bean
    public ItemWriter<Scan> writer() {    	
        return new CassandraBatchItemWriter();
    }

    @Bean
    @StepScope
    @Value("#{jobExecutionContext[lastIdPulled]}") 
    public RepositoryItemReader<Scan> myItemReader(Integer lastIdPulled) {
    	ScanItemReader reader = new ScanItemReader(lastIdPulled);
    	
        return reader;
    }
    
    @Bean
    public CassandraItemProcessor processor() {
        return new CassandraItemProcessor();
    }

    
   
    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener) {
        return jobBuilderFactory.get("importSSCScanTableJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(retrieveLastFetchedId())
                .next(moveLatestScan())
                .end()
                .build();
    }

    @Bean
    public Step retrieveLastFetchedId() {
        return stepBuilderFactory.get("retrieveLastFetchedId").tasklet(tasklet).build();
    }
        
    @Bean
    public Step moveLatestScan() {
    	
        return stepBuilderFactory.get("moveLatestScan")
                .<Scan, Scan> chunk(1000)
                .reader(myItemReader(null))
                .processor(processor())
                .writer(writer())
                .build();
    }
    
   
    // end::jobstep[]
}
