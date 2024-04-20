package log.qushe8r.stockdiscussionforum.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;

    @Value("${application.domain}")
    private String domain;

    @Value("${application.port}")
    private String port;

    public void sendVerificationEmail(String toEmail, Long userId, String verificationCode) {
        if (port != null) {
            domain = domain + ":" + port;
        }
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("이메일 인증 코드");
        message.setText("회원가입을 완료하려면 아래 링크를 클릭하세요.\n\n" +
                "인증 코드: " + verificationCode + "\n\n" +
                "링크: http://" + domain + "/api/sign-up/verify?userId=" + userId + "&code=" + verificationCode);

        javaMailSender.send(message);
    }
}
