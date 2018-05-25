package com.yuratrumpe.dao;

import com.yuratrumpe.model.Role;

public interface RoleDao extends GenericDao<Role> {

    Role loadRoleByName(String roleName);
}
