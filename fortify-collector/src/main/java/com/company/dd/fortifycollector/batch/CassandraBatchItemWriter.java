package com.company.dd.fortifycollector.batch;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Component;

import com.company.dd.fortifycollector.fortify.rdbms.model.Scan;
/*
import com.verizon.dd.fortifycollector.cassandra.model.CScan;
import com.verizon.dd.fortifycollector.cassandra.repository.ReactiveScanRepository;

@Component
@JobScope
public class CassandraBatchItemWriter implements ItemWriter<CScan> {

	
	private final ReactiveScanRepository<CScan> reactiveScanRepo;
	
	@Autowired(required = true)
	public CassandraBatchItemWriter(ReactiveScanRepository<CScan> reactiveScanRepo) {
		this.reactiveScanRepo = reactiveScanRepo;
	}


	@Override
	public void write(List<? extends CScan> scans) throws Exception {
		Iterator<? extends CScan> it = scans.iterator();
		@SuppressWarnings("unchecked")
		List<CScan> list = (List<CScan>)(List<?>)scans;
		//this.reactiveScanRepo.saveAll(list);
		
	}


}
*/
@Component
@JobScope
public class CassandraBatchItemWriter implements ItemWriter<Scan> {

    @Autowired
    private CassandraOperations cassandraTemplate;

	@Override
	public void write(List<? extends Scan> items) throws Exception {
		System.out.println("writing to cassandra");
		String insertPreparedCql = "insert into fortify_ssc_scan (tool_source_id, id, upload_status, artifact_id, audit_updated, certification, data_version, elapsed_time, engine_type, engine_version, entry_name, executable_loc, fortify_annotations_loc, guid, has_issue, host_name, is_completed, migrated, object_version, proj_id, proj_name, proj_ver_id, proj_ver_name, project_label, sca_build_id, sca_files, sca_label, server_version, start_date, total_loc, update_date, updated, version_label) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		List<List<?>> scanList  = new ArrayList<>();
		Iterator<? extends Scan> it = items.iterator();
		while(it.hasNext()){
			Scan p = it.next();
			List<Object> singleProjArgsList = new ArrayList<>();
			singleProjArgsList.add(UUID.fromString("8df71630-07ac-11e8-a307-f9a6ea4c7715"));
			singleProjArgsList.add(p.getId());
			singleProjArgsList.add(p.getUploadStatus());
			singleProjArgsList.add(p.getArtifact_id());
			singleProjArgsList.add(p.getAuditUpdated());
			singleProjArgsList.add(p.getCertification());
			singleProjArgsList.add(p.getDataVersion());
			singleProjArgsList.add(p.getElapsedTime());
			singleProjArgsList.add(p.getEngineType());
			singleProjArgsList.add(p.getEngineVersion());
			singleProjArgsList.add(p.getEntryName());
			singleProjArgsList.add(p.getExecutableLoc());
			singleProjArgsList.add(p.getFortifyAnnotationsLoc());
			singleProjArgsList.add(p.getGuid());
			singleProjArgsList.add(p.getHasIssue());
			singleProjArgsList.add(p.getHostName());
			singleProjArgsList.add(p.getIsCompleted());
			singleProjArgsList.add(p.getMigrated());
			singleProjArgsList.add(p.getObjectVersion());
			singleProjArgsList.add(p.getProjectVersion().getProject().getId());
			singleProjArgsList.add(p.getProjectVersion().getProject().getName());
			singleProjArgsList.add(p.getProjectVersion().getId());
			singleProjArgsList.add(p.getProjectVersion().getName());
			singleProjArgsList.add(p.getProjectLabel());
			singleProjArgsList.add(p.getScaBuildId());
			singleProjArgsList.add(p.getScaFiles());
			singleProjArgsList.add(p.getScaLabel());
			singleProjArgsList.add(p.getServerVersion());			
			singleProjArgsList.add(Date.from(Instant.ofEpochMilli(p.getStartDate())));			
			singleProjArgsList.add(p.getTotalLoc());
			singleProjArgsList.add(p.getUpdateDate());
			singleProjArgsList.add(p.getUpdated());
			singleProjArgsList.add(p.getVersionLabel());
			scanList.add(singleProjArgsList);
			
		}
		cassandraTemplate.ingest(insertPreparedCql, scanList);
		
		System.out.println("done writing to cassandra");
		
	}


}