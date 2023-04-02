package com.example.demo.repositry;

import com.example.demo.entity.StudentEnity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<StudentEnity,Long> {
}
