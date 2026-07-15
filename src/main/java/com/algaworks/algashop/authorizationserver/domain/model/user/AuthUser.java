package com.algaworks.algashop.authorizationserver.domain.model.user;

import com.algaworks.algashop.authorizationserver.domain.model.AbstractAuditableAggregateRoot;
import com.algaworks.algashop.authorizationserver.domain.model.IdGenerator;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "auth_user")
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthUser extends AbstractAuditableAggregateRoot<AuthUser> {

    @Id
    @EqualsAndHashCode.Include
    @Setter(AccessLevel.PRIVATE)
    private UUID id;

    @Setter(AccessLevel.PRIVATE)
    private String email;
    @Setter
    private String password;
    @Setter
    private String name;
    @Setter
    private boolean enabled;

    @Setter
    @Enumerated(EnumType.STRING)
    private AuthUserType type;

    public static AuthUser brandNew(
            String email,
            String name,
            AuthUserType type,
            String passwordHash
    ){
        AuthUser authUser = new AuthUser();
        authUser.setId(IdGenerator.generateTimeBasedUUID());
        authUser.setEmail(email);
        authUser.setName(name);
        authUser.setType(type);
        authUser.setPassword(passwordHash);
        authUser.setEnabled(true);

        return authUser;
    }

    public void anonymize() {
        this.setName("Anonymized User");
        this.setEmail("anonymized-" + this.id + "@deleted.local");
        this.setEnabled(false);
    }

}
