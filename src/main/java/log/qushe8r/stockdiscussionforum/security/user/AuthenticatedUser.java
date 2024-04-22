package log.qushe8r.stockdiscussionforum.security.user;

import io.jsonwebtoken.Claims;
import log.qushe8r.stockdiscussionforum.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
public class AuthenticatedUser implements UserDetails {
    private Long userId;
    private String username;
    private String password;
    private String authority;

    public static AuthenticatedUser toAuthenticatedUser(User user) {
        return new AuthenticatedUser(user.getId(), user.getUsername(), user.getPassword(), user.getRole().toString());
    }

    public static AuthenticatedUser toAuthenticatedUser(Claims claims) {
        Long userId = Long.parseLong(claims.get("userId", String.class));
        return new AuthenticatedUser(userId, claims.getSubject(), "", claims.get("role", String.class));
    }

    public Map<String, String> getClaims() {
        return Map.of("role", authority, "userId", String.valueOf(userId));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(authority));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
