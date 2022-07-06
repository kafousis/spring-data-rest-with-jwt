package com.github.kafousis.entities;

import lombok.*;
import lombok.experimental.Accessors;
import com.github.kafousis.enums.PrivilegeCategory;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity @Table(name = "privileges")
@Getter @Setter @NoArgsConstructor
@EqualsAndHashCode @ToString @Accessors(chain = true)
public class Privilege {

    @Id
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Privilege name cannot be blank.")
    @Column(nullable = false, unique = true)
    private String name;

    @NotNull(message = "Privilege category cannot be null.")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PrivilegeCategory category;

    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "privileges")
    private Set<Role> roles = new HashSet<>();
}
