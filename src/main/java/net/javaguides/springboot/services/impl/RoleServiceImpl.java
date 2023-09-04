package net.javaguides.springboot.services.impl;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Role;
import net.javaguides.springboot.payloads.RoleDTO;
import net.javaguides.springboot.repository.RoleRepository;
import net.javaguides.springboot.services.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RoleDTO createRole(RoleDTO roleDTO) {
        Role role = this.modelMapper.map(roleDTO, Role.class);
        Role addedRole = this.roleRepository.save(role);
        return this.modelMapper.map(addedRole, RoleDTO.class);
    }

    @Override
    public RoleDTO updateRole(RoleDTO roleDTO, Integer roleId) {
        Role role = this.roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role ", "Role Id", roleId));

       role.setName(roleDTO.getName());

        Role updatedRole = this.roleRepository.save(role);
        return this.modelMapper.map(updatedRole, RoleDTO.class);
    }

    @Override
    public void deleteRole(Integer roleId) {
        Role role = this.roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role ", "Role id", roleId));
        this.roleRepository.delete(role);
    }

    @Override
    public RoleDTO getRole(Integer roleId) {
        Role role = this.roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role", "Role id", roleId));
        return this.modelMapper.map(role, RoleDTO.class);
    }

    @Override
    public List<RoleDTO> getRoles() {
        List<Role> roles = this.roleRepository.findAll();
        List<RoleDTO> rolesDto = roles.stream().map((role) -> this.modelMapper.map(role, RoleDTO.class))
                .collect(Collectors.toList());
        return rolesDto;
    }
}
