package com.company.dd.fortifycollector.fortify.rdbms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "project")
@Getter @Setter @NoArgsConstructor @ToString
public class Project {

	@Id
	@Column(name = "id") 
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "creationDate")
	private String creationDate;
	
	@Column(name = "createdBy")
	private String createdBy;
}
