package uz.excel.onlineexcel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.excel.onlineexcel.entity.Organization;
import uz.excel.onlineexcel.repository.BaseRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long>, BaseRepository {
}
