package com.example.springServer.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "user")
public class User {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "study_group")
    private Group group;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private RoleEntity role;

    @OneToMany(mappedBy = "creator")
    private Set<Chat> ownedChats;

    @ManyToMany
    @JoinTable(name = "user_chat",
            joinColumns = @JoinColumn(name = "chat_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id",
                    referencedColumnName = "id"))
    private Set<Chat> chats;
}
