package com.algaworks.algashop.authorizationserver.application.user.query;

import com.algaworks.algashop.authorizationserver.domain.model.user.AuthUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthUserQueryService {

    private final AuthUserRepository authUserRepository;

    public AuthUserOutput findById(UUID id) {
        return authUserRepository.findById(id)
                .map(AuthUserOutput::from)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

}
