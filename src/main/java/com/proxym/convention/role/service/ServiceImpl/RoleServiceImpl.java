package com.proxym.convention.role.service.ServiceImpl;

import com.proxym.convention.role.dao.RoleRepository;
import com.proxym.convention.role.entities.Role;
import com.proxym.convention.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleDao;
@Override
    public Role createNewRole(Role role) {
        return roleDao.save(role);
    }
    @Override
  public Role findByRoleName(String nom) {
    return roleDao.findByRoleName(nom);
  }

}
