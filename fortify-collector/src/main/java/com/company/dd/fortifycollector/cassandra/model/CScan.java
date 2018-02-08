package com.company.dd.fortifycollector.cassandra.model;
/*
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Table;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "fortify_ssc_scan")
@Getter @Setter @NoArgsConstructor  @AllArgsConstructor @ToString
public class CScan {
	
	@PrimaryKey
	private CScanPrimaryKey toolSourceIdKey;

	@Column(name = "is_completed") 
	private String isCompleted;
	
	@Column(name = "update_date") 
	private LocalDateTime updateDate;
	
	@Column(name = "certification") 
	private String certification;
	
	@Column(name = "audit_updated") 
	private String auditUpdated;
	
	@Column(name = "sca_label") 
	private String scaLabel;
	
	@Column(name = "sca_build_id") 
	private String scaBuildId;
	
	@Column(name = "host_name") 
	private String hostName;
	
	@Column(name = "start_date") 
	private LocalDateTime startDate;
	
	@Column(name = "elapsed_time") 
	private int elapsedTime;
	
	@Column(name = "has_issue") 
	private String hasIssue;
	
	@Column(name = "updated") 
	private String updated;
	
	@Column(name = "sca_files") 
	private int scaFiles;
	
	@Column(name = "executable_loc") 
	private int executableLoc;
	
	@Column(name = "total_loc") 
	private int totalLoc;
	
	@Column(name = "engine_type") 
	private String engineType;
	
	@Column(name = "engine_version") 
	private String engineVersion;
	
	@Column(name = "guid") 
	private String guid;
	
	@Column(name = "project_label") 
	private String projectLabel;
	
	@Column(name = "version_label") 
	private String versionLabel;
		    
	@Column(name = "proj_ver_id")
	private int projVerId;
	
	@Column(name = "proj_ver_name")
	private String projVerName;
	
	@Column(name = "proj_id")
	private int projId;
	
	@Column(name = "proj_name")
	private String projName;
	
	@Column(name = "artifact_id") 
	private int artifact_id;
	
	@Column(name = "object_version") 
	private int objectVersion;
	
	@Column(name = "migrated") 
	private String migrated;
	
	@Column(name = "server_version") 
	private Float serverVersion;
	
	@Column(name = "entry_name") 
	private String entryName;

	
	@Column(name = "fortify_annotations_loc") 
	private String fortifyAnnotationsLoc;
	
	@Column(name = "data_version") 
	private String dataVersion;	
		
}
*/