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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "projectversion")
@Getter @Setter @NoArgsConstructor @ToString
public class ProjectVersion {

	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "active")
	private Character active;
	
	@Column(name = "creationDate") @Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	
	@JoinColumn(name = "project_id")
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)    
	private Project project;
	
}
