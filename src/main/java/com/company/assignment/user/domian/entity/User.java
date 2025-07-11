package com.company.assignment.user.domian.entity;

import com.company.assignment.common.converter.AES256Converter;
import com.company.assignment.common.converter.PasswordConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "assignment_user")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    @Convert(converter = AES256Converter.class)
    private String userId;

    @Builder.Default
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @Column(nullable = false, length = 60)
    @Convert(converter = PasswordConverter.class)
    private String password;

    @Column(nullable = false, length = 24)
    @Convert(converter = AES256Converter.class)
    private String name;
}
