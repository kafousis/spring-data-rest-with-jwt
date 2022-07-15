package com.github.kafousis.entities;

import lombok.*;
import lombok.experimental.Accessors;
import com.github.kafousis.enums.PrivilegeCategory;

import javax.persistence.*;
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

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PrivilegeCategory category;

    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "privileges")
    private Set<Role> roles = new HashSet<>();
}
