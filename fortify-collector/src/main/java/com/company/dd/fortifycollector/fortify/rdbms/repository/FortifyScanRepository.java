package com.company.dd.fortifycollector.fortify.rdbms.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.company.dd.fortifycollector.fortify.rdbms.model.Scan;

@Repository
public interface FortifyScanRepository extends JpaRepository<Scan, Integer> {

	
	@Query("select s from Scan s left join s.projectVersion where s.id > :#{#id}")
	Page<Scan> findAllScan(final Pageable pageable);
	
	@Query("select s from Scan s left join s.projectVersion where s.id > ?")
	Page<Scan> findByScanGreaterThanId(int id, final Pageable pageable);

}
