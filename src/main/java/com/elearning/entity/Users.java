package com.elearning.entity;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String fullName;
    @NonNull
    @Email
    private String email;
    @NonNull
    @Size(min=4, max=12)
    private String password;
    private String avatar;
    private String phone;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="role_id")
    private Roles role;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_courses",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Courses> courses;

    public Users() {

    }
}
