package com.yuratrumpe.model.converter;

import com.yuratrumpe.model.Role;
import com.yuratrumpe.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class StringToRoleConverter implements Converter<String,Role>{

    @Autowired
    private RoleService roleService;

    @Nullable
    @Override
    public Role convert(String s) {
        return roleService.getRoleByName(s);
    }
}
