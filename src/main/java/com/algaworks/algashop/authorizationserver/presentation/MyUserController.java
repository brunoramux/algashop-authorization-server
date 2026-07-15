package com.algaworks.algashop.authorizationserver.presentation;

import com.algaworks.algashop.authorizationserver.application.security.SecurityCheckApplicationService;
import com.algaworks.algashop.authorizationserver.application.user.management.AuthUserUpdateInput;
import com.algaworks.algashop.authorizationserver.application.user.query.AuthUserFilter;
import com.algaworks.algashop.authorizationserver.application.user.query.AuthUserOutput;
import com.algaworks.algashop.authorizationserver.application.user.query.AuthUserQueryService;
import com.algaworks.algashop.authorizationserver.application.user.query.PageModel;
import com.algaworks.algashop.authorizationserver.infrastructure.security.check.SecurityAnnotations;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users/me")
@RequiredArgsConstructor
public class MyUserController {

    private final SecurityCheckApplicationService securityCheckApplicationService;
    private final AuthUserQueryService authUserQueryService;

    @GetMapping
    @SecurityAnnotations.CanAccessOwnProfile
    public AuthUserOutput getMe(){
        UUID authenticatedUserId = securityCheckApplicationService.getAuthenticatedUserId();

        return authUserQueryService.findById(authenticatedUserId);
    }


}
