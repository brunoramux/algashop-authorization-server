package com.algaworks.algashop.authorizationserver.application.user.management;

import com.algaworks.algashop.authorizationserver.application.user.query.AuthUserOutput;
import com.algaworks.algashop.authorizationserver.domain.model.user.AuthUser;
import com.algaworks.algashop.authorizationserver.domain.model.user.AuthUserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthUserManagementApplicationService {

    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder  passwordEncoder;

    public AuthUserOutput create(AuthUserInput authUserInput) {
        boolean existsByEmail = authUserRepository.existsByEmail(authUserInput.getEmail());

        if (existsByEmail) {
            throw new AuthUserAlreadyExistsException("User with email " + authUserInput.getEmail() + " already exists");
        }

        String tempPassword = RandomStringUtils.secure().nextAlphanumeric(12);
        System.out.println(tempPassword);

        String passwordHash = passwordEncoder.encode(tempPassword);

        AuthUser authUser = AuthUser.brandNew(
                authUserInput.getEmail(),
                authUserInput.getName(),
                authUserInput.getType(),
                passwordHash
        );
        AuthUser savedUser = authUserRepository.save(authUser);
        return AuthUserOutput.from(savedUser);
    }

}
