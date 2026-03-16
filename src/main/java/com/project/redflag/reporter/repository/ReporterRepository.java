package com.project.redflag.reporter.repository;

import com.project.redflag.reporter.entity.ReporterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReporterRepository extends JpaRepository<ReporterEntity, Long> {
}
