package com.annm.zilliqa_project.service.serviceImpl;

import com.annm.zilliqa_project.dto.UserDto;
import com.annm.zilliqa_project.entity.Blocks;
import com.annm.zilliqa_project.entity.Roles;
import com.annm.zilliqa_project.entity.Users;
import com.annm.zilliqa_project.repository.RoleRepository;
import com.annm.zilliqa_project.repository.UserRepository;
import com.annm.zilliqa_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    public Users findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Users save(UserDto userDto) {
        Users users = new Users();
        users.setFirstName(userDto.getFirstName());
        users.setLastName(userDto.getLastName());
        users.setUsername(userDto.getUsername());
        users.setPassword(userDto.getPassword());
        users.setRoles(Arrays.asList(roleRepository.findByName("USER")));
        return userRepository.save(users);
    }

    @Override
    public Page<Users> getAllUsers(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 5);
        List<Users> usersList = this.allUsers();
        Page<Users> usersPage = toPage(usersList, pageable);
        return usersPage;
    }

    @Override
    public List<Users> allUsers() {
        List<Users> users = userRepository.findAll();
        return users;
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Users getById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public Users update(Users users) {
        Users user = userRepository.getById(users.getId());
        user.setUsername(users.getUsername());
        user.setPassword(users.getPassword());
        user.setLastName(users.getLastName());
        user.setFirstName(users.getFirstName());
        return userRepository.save(user);
    }


    private Page toPage(List<Users> list, Pageable pageable){
        if (pageable.getOffset() >= list.size()){
            return Page.empty();
        }
        int startIndex = (int) pageable.getOffset();
        int endIndex = ((pageable.getOffset() + pageable.getPageSize()) >= list.size())
                ? list.size() : (int) (pageable.getOffset() + pageable.getPageSize());
        List subList = list.subList(startIndex, endIndex);
        return new PageImpl(subList, pageable, list.size());
    }

}
