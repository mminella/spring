package com.company.dd.fortifycollector.fortify.rdbms.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "scan")
@Getter @Setter @NoArgsConstructor @ToString
public class Scan {
	
	@Id @Column(name = "id") 
	private int id;
	
	@Column(name = "isCompleted") 
	private String isCompleted;
	
	@Column(name = "updateDate") 
	private Date updateDate;
	
	@Column(name = "certification") 
	private String certification;
	
	@Column(name = "auditUpdated") 
	private String auditUpdated;
	
	@Column(name = "scaLabel") 
	private String scaLabel;
	
	@Column(name = "scaBuildId") 
	private String scaBuildId;
	
	@Column(name = "hostName") 
	private String hostName;
	
	@Column(name = "startDate") 
	private long startDate;
	
	@Column(name = "elapsedTime") 
	private int elapsedTime;
	
	@Column(name = "hasIssue") 
	private String hasIssue;
	
	@Column(name = "updated") 
	private String updated;
	
	@Column(name = "scaFiles") 
	private int scaFiles;
	
	@Column(name = "executableLoc") 
	private int executableLoc;
	
	@Column(name = "totalLoc") 
	private int totalLoc;
	
	@Column(name = "engineType") 
	private String engineType;
	
	@Column(name = "engineVersion") 
	private String engineVersion;
	
	@Column(name = "guid") 
	private String guid;
	
	@Column(name = "projectLabel") 
	private String projectLabel;
	
	@Column(name = "versionLabel") 
	private String versionLabel;
	
	@JoinColumn(name = "projectVersion_id")
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)    	
	private ProjectVersion projectVersion  ;
	
	@Column(name = "artifact_id") 
	private int artifact_id;
	
	@Column(name = "objectVersion") 
	private int objectVersion;
	
	@Column(name = "migrated") 
	private String migrated;
	
	@Column(name = "serverVersion") 
	private Float serverVersion;
	
	@Column(name = "entryName") 
	private String entryName;
	
	@Column(name = "uploadStatus") 
	private String uploadStatus;
	
	@Column(name = "fortifyAnnotationsLoc") 
	private String fortifyAnnotationsLoc;
	
	@Column(name = "dataVersion") 
	private String dataVersion;
		
}
