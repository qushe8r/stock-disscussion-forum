package log.qushe8r.stockdiscussionforum.security.service;

import log.qushe8r.stockdiscussionforum.security.user.AuthenticatedUser;
import log.qushe8r.stockdiscussionforum.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticatedUserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(AuthenticatedUser::toAuthenticatedUser)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
