package ma.formations.service;

import lombok.AllArgsConstructor;
import ma.formations.dao.PermissionRepository;
import ma.formations.dao.RoleRepository;
import ma.formations.dao.UserRepository;
import ma.formations.dto.user.PermissionVo;
import ma.formations.dto.user.RoleVo;
import ma.formations.dto.user.UserVo;
import ma.formations.model.Permission;
import ma.formations.model.Role;
import ma.formations.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements IUserService, UserDetailsService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private ModelMapper modelMapper;
    private PermissionRepository permissionRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        UserVo userVo = modelMapper.map(user, UserVo.class);
        List<RoleVo> permissions = new ArrayList<>();

        userVo.getAuthorities().forEach(
                roleVo -> roleVo.getAuthorities().forEach(
                        permission -> permissions.add(
                                RoleVo.builder().authority(permission.getAuthority()).build())));

        userVo.getAuthorities().addAll(permissions);

        return userVo;
    }

    @Override
    public void save(UserVo userVo) {
        User user = modelMapper.map(userVo, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthorities(user.getAuthorities().stream()
                .map(bo -> roleRepository.findByAuthority(bo.getAuthority())
                        .orElseThrow(() -> new RuntimeException("Role not found: " + bo.getAuthority())))
                .collect(Collectors.toList()));
        userRepository.save(user);
    }

    @Override
    public void save(RoleVo roleVo) {
        Role role = modelMapper.map(roleVo, Role.class);
        role.setAuthorities(role.getAuthorities().stream()
                .map(bo -> permissionRepository.findByAuthority(bo.getAuthority()))
                .collect(Collectors.toList()));
        roleRepository.save(role);
    }

    @Override
    public void save(PermissionVo vo) {
        permissionRepository.save(modelMapper.map(vo, Permission.class));
    }

    @Override
    public RoleVo getRoleByName(String authority) {
        return modelMapper.map(
                roleRepository.findByAuthority(authority)
                        .orElseThrow(() -> new RuntimeException("Role not found: " + authority)),
                RoleVo.class);
    }

    @Override
    public PermissionVo getPermissionByName(String authority) {
        Permission permission = permissionRepository.findByAuthority(authority);
        if (permission == null) {
            throw new RuntimeException("Permission not found: " + authority);
        }
        return modelMapper.map(permission, PermissionVo.class);
    }
}