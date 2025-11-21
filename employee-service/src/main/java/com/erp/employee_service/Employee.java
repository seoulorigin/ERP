package com.erp.employee_service;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity // 해당 클래스(Employee) DB table과 일대일 매핑
@Table(name = "employees")
@Data // Getter, Setter, toString 등 method 자동 생성
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String department;

    @Column(nullable = false, length = 100)
    private String position;

    @CreationTimestamp // create_at
    @Column(updatable = false)
    private LocalDateTime createdAt;
}