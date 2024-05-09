package log.qushe8r.stockdiscussionforum.userservice.adapter.out.web;

import log.qushe8r.stockdiscussionforum.common.WebAdapter;
import log.qushe8r.stockdiscussionforum.userservice.application.port.out.UserRegistrationVerificationEvent;
import log.qushe8r.stockdiscussionforum.userservice.application.port.out.UserRegistrationVerificationMailPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;

@WebAdapter
@RequiredArgsConstructor
public class UserRegistrationVerificationMailAdapter implements UserRegistrationVerificationMailPort {

    private final JavaMailSender javaMailSender;

    @Value("${gateway.domain}")
    private String domain;

    @Value("${gateway.port}")
    private String port;

    @EventListener
    @Async
    public void send(UserRegistrationVerificationEvent event) {
        if (port != null) {
            domain = domain + ":" + port;
            port = null;
        }

        SimpleMailMessage message = new SimpleMailMessage();
        Long userId = event.userId();
        String email = event.username();
        String verificationCode = event.verificationCode();

        message.setTo(email);
        message.setSubject("이메일 인증 코드");
        message.setText("회원가입을 완료하려면 아래 링크를 클릭하세요.\n\n" +
                "인증 코드: " + verificationCode + "\n\n" +
                "링크: http://" + domain + "/api/sign-up/verify?userId=" + userId + "&code=" + verificationCode);

        javaMailSender.send(message);
    }

}
