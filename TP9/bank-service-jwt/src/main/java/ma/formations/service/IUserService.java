package ma.formations.service;

import ma.formations.dto.user.PermissionVo;
import ma.formations.dto.user.RoleVo;
import ma.formations.dto.user.UserVo;

public interface IUserService {
    void save(UserVo user);
    void save(RoleVo role);
    void save(PermissionVo vo);
    RoleVo getRoleByName(String role);
    PermissionVo getPermissionByName(String authority);
}