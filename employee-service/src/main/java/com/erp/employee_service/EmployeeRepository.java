package com.erp.employee_service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JpaRepository<Entity, PK type>
 * SQL 쿼리 작성없이 Spring이 자동으로 CRUD 기능 생성
 */

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {}