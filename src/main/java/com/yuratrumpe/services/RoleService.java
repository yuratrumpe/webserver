package com.yuratrumpe.services;

import com.yuratrumpe.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAllRoles();

    Role getRoleByName(String roleName);
}
