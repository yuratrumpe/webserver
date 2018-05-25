package com.yuratrumpe.services;

import com.yuratrumpe.dao.RoleDao;
import com.yuratrumpe.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> getAllRoles() {
        return roleDao.loadAll();
    }

    @Override
    public Role getRoleByName(String roleName) {
        return roleDao.loadRoleByName(roleName);
    }
}
