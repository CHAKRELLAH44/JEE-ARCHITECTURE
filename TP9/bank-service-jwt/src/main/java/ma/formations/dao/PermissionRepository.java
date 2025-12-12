package ma.formations.dao;

import ma.formations.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {
    Permission findByAuthority(String authority);
}