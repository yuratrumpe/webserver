package com.yuratrumpe.model;


import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @Column(name = "id" , updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String rolename;

    @Override
    public String getAuthority() {
        return rolename;
    }

    public Long getId() {
        return id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (getId() != null ? !getId().equals(role.getId()) : role.getId() != null) return false;
        return getRolename() != null ? getRolename().equals(role.getRolename()) : role.getRolename() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getRolename() != null ? getRolename().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return rolename;
    }
}
