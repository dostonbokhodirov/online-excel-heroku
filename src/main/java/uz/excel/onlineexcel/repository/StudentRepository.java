package uz.excel.onlineexcel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uz.excel.onlineexcel.dto.FilterDto;
import uz.excel.onlineexcel.entity.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long>, BaseRepository {


    @Transactional
    @Modifying
    @Query(value = "select * from auth.student order by entrance_year desc limit 50", nativeQuery = true)
    List<Student> findAllByCount();



}
