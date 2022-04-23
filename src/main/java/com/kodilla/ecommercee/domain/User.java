package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.enums.AppRoles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "USERS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="USER_ID")
    private Long userId;

    @OneToMany(
            targetEntity = Order.class,
            mappedBy="user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Order> orders;

    @Column(name = "KEY_ID")
    private String keyId;

    @NotNull
    @Column(name = "USERNAME")
    private String username;

    @NotNull
    @Column(name = "PASSWORD")
    private String password;

    @NotNull
    @Column(name="USER_BLOCKED")
    private Boolean userBlocked=Boolean.FALSE;

    @OneToOne(mappedBy = "user")
    private Cart cart;

    @Column(name = "ROLE")
    @Enumerated(value = EnumType.STRING)
    private AppRoles role;


    public User(String username, String password, AppRoles role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public void setUserBlocked(Boolean userBlocked) {
        this.userBlocked = userBlocked;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public User(Long userId, String username, String password,AppRoles role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = AppRoles.USER;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = AppRoles.USER;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getGrantedAuthority();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}