package net.javaguides.springboot.services;

import net.javaguides.springboot.payloads.RoleDTO;

import java.util.List;

public interface RoleService {

    // create
    RoleDTO createRole(RoleDTO roleDTO);

    // update
    RoleDTO updateRole(RoleDTO roleDTO, Integer roleId);

    // delete
    void deleteRole(Integer departmentId);

    // get
    RoleDTO getRole(Integer roleId);

    // get All
    List<RoleDTO> getRoles();
}
