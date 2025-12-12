package ma.formations.controller;

import lombok.AllArgsConstructor;
import ma.formations.dto.TokenVo;
import ma.formations.dto.user.CreateUserRequest;
import ma.formations.dto.user.RoleVo;
import ma.formations.dto.user.UserRequest;
import ma.formations.dto.user.UserVo;
import ma.formations.jwt.JwtUtils;
import ma.formations.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private IUserService userService;

    @PostMapping("/signin")
    public ResponseEntity<TokenVo> authenticateUser(@RequestBody UserRequest userRequest) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            userRequest.username(), userRequest.password()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            TokenVo tokenVo = TokenVo.builder()
                    .jwtToken(jwt)
                    .username(userRequest.username())
                    .roles(authentication.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority)
                            .collect(Collectors.toList()))
                    .build();

            return ResponseEntity.ok(tokenVo);
        } catch (Exception e) {
            throw new RuntimeException("Login ou mot de passe incorrect");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<String> createUser(@RequestBody CreateUserRequest createUserRequest) {
        userService.save(UserVo.builder()
                .username(createUserRequest.username())
                .password(createUserRequest.password())
                .email(createUserRequest.email())
                .enabled(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .authorities(List.of(RoleVo.builder().authority("ROLE_CLIENT").build()))
                .build());

        return new ResponseEntity<>(
                String.format("User [%s] created with success", createUserRequest.username()),
                HttpStatus.CREATED);
    }
}