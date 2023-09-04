package net.javaguides.springboot.controller;

import jakarta.validation.Valid;
import net.javaguides.springboot.payloads.ApiResponse;
import net.javaguides.springboot.payloads.DepartmentDTO;
import net.javaguides.springboot.payloads.RoleDTO;
import net.javaguides.springboot.services.DepartmentService;
import net.javaguides.springboot.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    // create Role
    @PostMapping("/")
    public ResponseEntity<RoleDTO> createRole(@Valid @RequestBody RoleDTO roleDTO) {
        RoleDTO createRole = this.roleService.createRole(roleDTO);
        return new ResponseEntity<>(createRole, HttpStatus.CREATED);
    }

    // update Role
    @PutMapping("/{roleId}")
    public ResponseEntity<RoleDTO> updateRole(@Valid @RequestBody RoleDTO roleDTO, @PathVariable int roleId) {
        RoleDTO updatedRole = this.roleService.updateRole(roleDTO, roleId);
        return new ResponseEntity<>(updatedRole, HttpStatus.CREATED);
    }

    // delete Role
    @DeleteMapping("/{roleId}")
    public ResponseEntity<ApiResponse> deleteRole(@PathVariable int roleId) {
        this.roleService.deleteRole(roleId);
        return new ResponseEntity<>(new ApiResponse("Role is deleted successfully !!", true),
                HttpStatus.OK);
    }

    // get Role
    @GetMapping("/{roleId}")
    public ResponseEntity<RoleDTO> getRole(@PathVariable int roleId) {
        RoleDTO roleDto = this.roleService.getRole(roleId);
        return new ResponseEntity<>(roleDto, HttpStatus.OK);
    }

    // get all Roles
    @GetMapping("/")
    public ResponseEntity<List<RoleDTO>> getRoles() {
        List<RoleDTO> roles = this.roleService.getRoles();
        return ResponseEntity.ok(roles);
    }
}
