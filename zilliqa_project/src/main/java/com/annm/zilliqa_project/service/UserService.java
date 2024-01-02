package com.annm.zilliqa_project.service;

import com.annm.zilliqa_project.dto.UserDto;
import com.annm.zilliqa_project.entity.Blocks;
import com.annm.zilliqa_project.entity.Exceptions;
import com.annm.zilliqa_project.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    public Users findByUsername(String username);

    public Users save(UserDto userDto);


    Page<Users> getAllUsers(int pageNo);
    List<Users> allUsers();

    void deleteById(Long id);

    Users getById(Long id);

    Users update(Users users);
}
