package com.peters.User_Registration_and_Email_Verification.service;

import com.peters.User_Registration_and_Email_Verification.entity.UserEntity;
import com.peters.User_Registration_and_Email_Verification.entity.UserRole;
import com.peters.User_Registration_and_Email_Verification.exception.RoleAlreadyExistException;
import com.peters.User_Registration_and_Email_Verification.exception.UserAlreadyExistsException;
import com.peters.User_Registration_and_Email_Verification.exception.UserNotFoundException;
import com.peters.User_Registration_and_Email_Verification.repository.IUserRepository;
import com.peters.User_Registration_and_Email_Verification.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleService implements IRoleService{
    private final RoleRepository roleRepository;
    private final IUserRepository userRepository;
    @Override
    public List<UserRole> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public UserRole createRole(UserRole role) {
        Optional<UserRole> roleOpt = roleRepository.findByName(role.getName());
        if(roleOpt.isPresent()){
            throw new RoleAlreadyExistException(roleOpt.get().getName()+ " role already exist!");
        }
        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(Long roleId) {
        this.removeAllUserFromRole(roleId);
        roleRepository.deleteById(roleId);
    }

    @Override
    public UserRole findByName(String name) {
        return roleRepository.findByName(name).get();
    }

    @Override
    public UserRole findById(Long roleId) {
        return roleRepository.findById(roleId).get();
    }

    @Override
    public UserEntity removeUserFromRole(Long userId, Long roleId) {
        Optional<UserEntity> user = userRepository.findById(userId);
        Optional<UserRole> role = roleRepository.findById(userId);
        if(role.isPresent() && role.get().getUsers().contains(user.get())){
            role.get().removeUserFromRole(user.get());
            roleRepository.save(role.get());
            return user.get();
        }
       throw new UserNotFoundException("User not found!");
    }

    @Override
    public UserEntity assignUserToRole(Long userId, Long roleId) {
        Optional<UserEntity> user = userRepository.findById(userId);
        Optional<UserRole> role = roleRepository.findById(userId);
        if(user.isPresent() && user.get().getRole().contains(role.get())){
            throw new UserAlreadyExistsException(user.get().getFirstName() + " is already assigned to the "+ role.get().getName() + " role");
        }
        role.ifPresent(assignRole -> assignRole.assignUserToRole(user.get()));
        roleRepository.save(role.get());
        return user.get();
    }

    @Override
    public UserRole removeAllUserFromRole(Long roleId) {
        Optional<UserRole> roleOpt = roleRepository.findById(roleId);
        roleOpt.ifPresent(UserRole ::removeAllUsersFromRole);
        return roleRepository.save(roleOpt.get());
    }
}
