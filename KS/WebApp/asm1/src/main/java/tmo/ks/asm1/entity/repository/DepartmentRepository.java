package tmo.ks.asm1.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tmo.ks.asm1.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, String> {
}