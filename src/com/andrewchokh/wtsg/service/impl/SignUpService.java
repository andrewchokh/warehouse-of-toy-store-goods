package com.andrewchokh.wtsg.service.impl;

import com.andrewchokh.wtsg.exception.MessageTemplate;
import com.andrewchokh.wtsg.exception.SignUpException;
import com.andrewchokh.wtsg.model.impl.User;
import com.andrewchokh.wtsg.persistence.repository.contracts.UserRepository;
import com.andrewchokh.wtsg.utils.ApplicationLogger;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Properties;
import java.util.UUID;
import java.util.function.Supplier;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.mindrot.bcrypt.BCrypt;

public class SignUpService {

    private static LocalDateTime codeCreationTime;
    private final UserRepository userRepository;

    public SignUpService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private static void sendVerificationCode(String email, String verificationCode) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "sandbox.smtp.mailtrap.io");
        properties.put("mail.smtp.port", "2525");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(System.getenv("MAIL_USERNAME"),
                    System.getenv("MAIL_PASSWORD"));
            }
        });

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress("no-reply@email.wtsg.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Verification Code");
            message.setText("Here's your verification code: " + verificationCode);

            Transport.send(message);

            ApplicationLogger.info("The message was send successfully.");

        } catch (MessagingException e) {
            throw new RuntimeException(
                "Error occurred while sending the message: %s".formatted(e.getMessage()));
        }
    }

    public static String generateAndSendVerificationCode(String email) {
        String verificationCode = String.valueOf((int) (Math.random() * 900000 + 100000));

        sendVerificationCode(email, verificationCode);

        codeCreationTime = LocalDateTime.now();

        return verificationCode;
    }

    public void signUp(String firstName, String lastName, String email, String password,
        User.Role role, Supplier<String> userInput) {
        String verificationCode = generateAndSendVerificationCode(email);

        verifyCode(userInput.get(), verificationCode);

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        userRepository.add(
            new User(UUID.randomUUID(), hashedPassword, email, firstName, lastName, role));

        ApplicationLogger.info("The verification code was confirmed.");
    }

    private void verifyCode(String inputCode, String generatedCode) {
        LocalDateTime currentTime = LocalDateTime.now();

        long secondsElapsed = ChronoUnit.SECONDS.between(codeCreationTime, currentTime);
        long expirationTime = Long.parseLong(
            System.getenv("MAIL_VERIFICATION_CODE_EXPIRATION_SECONDS"));

        if (secondsElapsed > expirationTime) {
            throw new SignUpException(MessageTemplate.EXPIRED_VERIFICATION_CODE.getTemplate());
        }

        if (!inputCode.equals(generatedCode)) {
            throw new SignUpException(MessageTemplate.INVALID_VERIFICATION_CODE.getTemplate());
        }

        codeCreationTime = null;
    }
}
