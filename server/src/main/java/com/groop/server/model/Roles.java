package com.groop.server.model;
import javax.persistence.*;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author joandy alejo garcia
 */
@Entity
public class Roles implements GrantedAuthority {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authority;
    @ManyToOne(optional = false)
    private User user;

    public Roles(){}

    public Roles(String authority) {
        this.authority = authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public User getMember() {
        return user;
    }

    public void setMember(User user) {
        this.user = user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
