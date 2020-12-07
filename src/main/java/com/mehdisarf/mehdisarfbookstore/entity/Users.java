package com.mehdisarf.mehdisarfbookstore.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
@NamedQueries(
        {
                @NamedQuery(name = "Users.findAll", query = "select u from Users u order by u.fullName"),
                @NamedQuery(name = "Users.countAll", query = "select count(*) from Users u"),
                @NamedQuery(name = "Users.findByEmail", query = "select u from Users u where u.email = :email"),
                @NamedQuery(name = "Users.checkLogin", query = "select u from Users u where u.email = :email and u.password = :pass")
        }
)
public class Users {

    private Integer userId;
    private String email;
    private String password;
    private String fullName;

    public Users() {
    }

    public Users(String email, String fullName, String password) {
        this.email = email;
        this.fullName = fullName;
        this.password = password;
    }

    public Users(int id, String email, String fullname, String password) {
        this(email, fullname, password);
        this.userId = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 30)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 16)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "full_name", nullable = false, length = 30)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return userId + ", " + email + '\'' + ", " + fullName + '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return Objects.equals(userId, users.userId) &&
                Objects.equals(email, users.email) &&
                Objects.equals(password, users.password) &&
                Objects.equals(fullName, users.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, email, password, fullName);
    }
}
