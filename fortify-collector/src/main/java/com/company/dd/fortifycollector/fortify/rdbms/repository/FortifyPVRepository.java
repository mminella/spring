package com.company.dd.fortifycollector.fortify.rdbms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.company.dd.fortifycollector.fortify.rdbms.model.ProjectVersion;

public interface FortifyPVRepository extends JpaRepository<ProjectVersion, Integer>{

	@Query("select pv from ProjectVersion pv left join fetch pv.project")
	List<ProjectVersion> findAllProjectVersions();
}
