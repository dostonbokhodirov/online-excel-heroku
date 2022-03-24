package uz.excel.onlineexcel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.excel.onlineexcel.entity.AuthUser;
import uz.excel.onlineexcel.enums.Status;
import uz.excel.onlineexcel.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long>, BaseRepository {


    Optional<AuthUser> findByIdAndStatus(Long id, Status status);

    Optional<List<AuthUser>> findByOrganizationIdAndStatus(Long organizationId, Status status);

    Optional<AuthUser> findByPhone(String phone);

    Optional<AuthUser> findByUsername(String phone);
}
