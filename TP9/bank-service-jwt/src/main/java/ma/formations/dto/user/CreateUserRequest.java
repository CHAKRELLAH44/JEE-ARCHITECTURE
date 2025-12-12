package ma.formations.dto.user;

public record CreateUserRequest(String username, String password, String email) {
}