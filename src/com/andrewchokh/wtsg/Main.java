package com.andrewchokh.wtsg;

import com.andrewchokh.wtsg.exception.JsonFileIOException;
import com.andrewchokh.wtsg.model.impl.User.Role;
import com.andrewchokh.wtsg.persistence.repository.RepositoryFactory;
import com.andrewchokh.wtsg.persistence.repository.contracts.UserRepository;
import com.andrewchokh.wtsg.service.impl.SignUpService;
import com.andrewchokh.wtsg.utils.ApplicationLogger;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws JsonFileIOException {
        ApplicationLogger.initializeLogger();

        RepositoryFactory jsonRepositoryFactory = RepositoryFactory
            .getRepositoryFactory(RepositoryFactory.JSON);
        UserRepository userRepository = jsonRepositoryFactory.getUserRepository();
        SignUpService signUpService = new SignUpService(userRepository);

        // Test Registration
        signUpService.signUp("Oleg", "Barsukov", "to@example.com", "Blago12345", Role.ADMIN, () -> {
            System.out.print("Enter verification code: ");

            try (Scanner scanner = new Scanner(System.in)) {
                return scanner.nextLine();
            } catch (Exception e) {
                ApplicationLogger.warning("Invalid argument for verification code.");
            }

            return null;
        });

        jsonRepositoryFactory.commit();
    }
}