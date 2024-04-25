package log.qushe8r.stockdiscussionforum.security.controller;

import log.qushe8r.stockdiscussionforum.security.service.JwtAuthService;
import log.qushe8r.stockdiscussionforum.security.user.AuthenticatedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtAuthService jwtAuthService;

    @PostMapping("/reissue")
    public ResponseEntity<Void> reissue(@RequestParam("refresh") String refreshToken) {
        HttpHeaders headers = jwtAuthService.reissue(refreshToken);
        return ResponseEntity.ok().headers(headers).build();
    }

    @PostMapping("/logout/all")
    public ResponseEntity<Void> logoutAllDevices(@AuthenticationPrincipal AuthenticatedUser authenticatedUser) {
        Long userId = authenticatedUser.getUserId();

        HttpHeaders headers = jwtAuthService.logoutAllDevices(userId);
        return ResponseEntity.ok().headers(headers).build();
    }

}
