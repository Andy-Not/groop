package com.groop.server.domain;
import javax.persistence.*;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author joandy alejo garcia
 */
@Entity
public class Roles implements GrantedAuthority {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String athority;
    @ManyToOne
    private Member member;

    public Roles(){}

    public Roles(String athority) {
        this.athority = athority;
    }

    public void setAthority(String athority) {
        this.athority = athority;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getAuthority() {
        return athority;
    }
}
