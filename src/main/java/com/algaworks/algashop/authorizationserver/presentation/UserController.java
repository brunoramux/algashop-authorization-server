package com.algaworks.algashop.authorizationserver.presentation;

import com.algaworks.algashop.authorizationserver.application.user.management.AuthUserInput;
import com.algaworks.algashop.authorizationserver.application.user.management.AuthUserManagementApplicationService;
import com.algaworks.algashop.authorizationserver.application.user.management.AuthUserUpdateInput;
import com.algaworks.algashop.authorizationserver.application.user.query.AuthUserFilter;
import com.algaworks.algashop.authorizationserver.application.user.query.AuthUserOutput;
import com.algaworks.algashop.authorizationserver.application.user.query.AuthUserQueryService;
import com.algaworks.algashop.authorizationserver.application.user.query.PageModel;
import com.algaworks.algashop.authorizationserver.infrastructure.security.check.SecurityAnnotations;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final AuthUserManagementApplicationService  authUserManagementApplicationService;
    private final AuthUserQueryService authUserQueryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @SecurityAnnotations.CanWriteUsers
    public AuthUserOutput save(@Valid @RequestBody AuthUserInput input) {
        return authUserManagementApplicationService.create(input);
    }

    @SecurityAnnotations.CanReadUsers
    @GetMapping
    public PageModel<AuthUserOutput> findAll(AuthUserFilter filter) {
        return authUserQueryService.findAll(filter);
    }

    @SecurityAnnotations.CanReadUsers
    @GetMapping("/{userId}")
    public AuthUserOutput findById(@PathVariable UUID userId) {
        return authUserQueryService.findById(userId);
    }

    @SecurityAnnotations.CanWriteUsers
    @PutMapping("/{userId}")
    public AuthUserOutput update(@PathVariable UUID userId, @RequestBody @Valid AuthUserUpdateInput input) {
        return authUserManagementApplicationService.update(userId, input);
    }

    @SecurityAnnotations.CanWriteUsers
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID userId) {
        authUserManagementApplicationService.delete(userId);
    }

}
