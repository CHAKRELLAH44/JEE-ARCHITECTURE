package ma.formations;

import ma.formations.dto.user.PermissionVo;
import ma.formations.dto.user.RoleVo;
import ma.formations.dto.user.UserVo;
import ma.formations.service.IUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class BankServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(IUserService userService) {
        return args -> {
            // Create permissions
            List<String> permissions = Arrays.asList(
                    "GET_ALL_CUSTOMERS",
                    "GET_CUSTOMER_BY_IDENTITY",
                    "CREATE_CUSTOMER",
                    "UPDATE_CUSTOMER",
                    "DELETE_CUSTOMER",
                    "GET_ALL_BANK_ACCOUNT",
                    "GET_BANK_ACCOUNT_BY_RIB",
                    "CREATE_BANK_ACCOUNT",
                    "ADD_WIRED_TRANSFER",
                    "GET_TRANSACTIONS"
            );

            permissions.forEach(perm ->
                    userService.save(PermissionVo.builder().authority(perm).build()));

            // Create ROLE_AGENT_GUICHET
            RoleVo roleAgentGuichet = RoleVo.builder()
                    .authority("ROLE_AGENT_GUICHET")
                    .authorities(List.of(
                            userService.getPermissionByName("GET_ALL_CUSTOMERS"),
                            userService.getPermissionByName("GET_CUSTOMER_BY_IDENTITY"),
                            userService.getPermissionByName("CREATE_CUSTOMER"),
                            userService.getPermissionByName("UPDATE_CUSTOMER"),
                            userService.getPermissionByName("DELETE_CUSTOMER"),
                            userService.getPermissionByName("GET_ALL_BANK_ACCOUNT"),
                            userService.getPermissionByName("GET_BANK_ACCOUNT_BY_RIB"),
                            userService.getPermissionByName("CREATE_BANK_ACCOUNT")))
                    .build();

            // Create ROLE_CLIENT
            RoleVo roleClient = RoleVo.builder()
                    .authority("ROLE_CLIENT")
                    .authorities(List.of(
                            userService.getPermissionByName("GET_CUSTOMER_BY_IDENTITY"),
                            userService.getPermissionByName("GET_BANK_ACCOUNT_BY_RIB"),
                            userService.getPermissionByName("ADD_WIRED_TRANSFER"),
                            userService.getPermissionByName("GET_TRANSACTIONS")))
                    .build();

            userService.save(roleAgentGuichet);
            userService.save(roleClient);

            // Create users
            UserVo agentGuichet = UserVo.builder()
                    .username("agentguichet")
                    .password("agentguichet")
                    .email("agent@bank.com")
                    .authorities(List.of(roleAgentGuichet))
                    .accountNonExpired(true)
                    .accountNonLocked(true)
                    .credentialsNonExpired(true)
                    .enabled(true)
                    .build();

            UserVo client = UserVo.builder()
                    .username("client")
                    .password("client")
                    .email("client@bank.com")
                    .authorities(List.of(roleClient))
                    .accountNonExpired(true)
                    .accountNonLocked(true)
                    .credentialsNonExpired(true)
                    .enabled(true)
                    .build();

            userService.save(agentGuichet);
            userService.save(client);

            System.out.println("✅ Database initialized successfully!");
            System.out.println("👤 Users created:");
            System.out.println("   - agentguichet / agentguichet (ROLE_AGENT_GUICHET)");
            System.out.println("   - client / client (ROLE_CLIENT)");
        };
    }
}