package com.yuratrumpe.dao;


import com.yuratrumpe.model.Role;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;

@Repository
@Transactional
public class RoleDaoImpl extends AbstractDao<Role> implements RoleDao {

    @Override
    public Role loadRoleByName(String roleName) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Role AS role WHERE role.rolename = :name");
        query.setParameter("name", roleName);
        try {
            Role role = (Role) query.getSingleResult();
            return role;
        } catch (NoResultException e) {
            return null;
        }
    }
}
