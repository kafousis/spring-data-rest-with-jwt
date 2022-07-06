package com.github.kafousis.entities;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity @Table(name = "roles")
@Getter @Setter @NoArgsConstructor
@EqualsAndHashCode @ToString @Accessors(chain = true)
public class Role {

    @Id
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Role name cannot be blank.")
    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @ManyToMany
    @JoinTable(
            name = "roles_privileges",
            joinColumns = { @JoinColumn(name = "role_id") },
            inverseJoinColumns = { @JoinColumn(name = "privilege_id") }
    )
    private Set<Privilege> privileges = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();
}
