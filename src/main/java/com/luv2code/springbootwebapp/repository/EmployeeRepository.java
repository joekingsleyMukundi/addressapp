package com.luv2code.springbootwebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luv2code.springbootwebapp.entity.Employee;

//@Repository annotation provides the database predifined methods
//the mothods include save, delete, findById, deleteById
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
