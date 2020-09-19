package com.elearning.services.impl;

import com.elearning.dto.UsersDTO;
import com.elearning.entity.Users;
import com.elearning.repository.CoursRepositoies;
import com.elearning.repository.RoleRepositories;
import com.elearning.repository.UserRepositories;
import com.elearning.services.UserServices;
import com.elearning.utilities.mapper.UserMapper;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class UserServicesImpl implements UserServices {
    private final UserRepositories userRepositories;

    @Autowired
    RoleRepositories roleRepositories;
    @Autowired
    CoursRepositoies coursRepositoies;
    @Autowired
    public UserServicesImpl(UserRepositories userRepositories) {
        this.userRepositories = userRepositories;
    }

    @Override
    public List<UsersDTO> getAll() {
        List<Users> list = userRepositories.findAll();
        List<UsersDTO> listDTO = new LinkedList<UsersDTO>();
        for (Users user : list) {
            listDTO.add(UserMapper.toUserDTO(user));
        }
        return listDTO;
    }

    @Override
    public void add(UsersDTO dto) {
        userRepositories.save(UserMapper.toUser(dto, roleRepositories.findByDescription(dto.getRoleDesc())));
    }

    @Override
    public UsersDTO findById(int id) {
        return UserMapper.toUserDTO(userRepositories.findById(Integer.valueOf(id)).get());
    }

    @Override
    public void update(UsersDTO dto) {
        Users user = userRepositories.findById(dto.getId()).get();
        user.setEmail(dto.getEmail());
        user.setPassword(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt(12)));
        user.setFullName(dto.getFullName());
        user.setAvatar(dto.getAvatar());
        user.setPhone(dto.getPhone());

        userRepositories.save(user);
    }

    @Override
    public void delete(int id) {
        userRepositories.deleteById(id);
    }

    @Override
    public List<UsersDTO> search(String keyword) {
        List<UsersDTO> dtos = new ArrayList<UsersDTO>();

        List<Users> list = userRepositories.search(keyword);
        for (Users users : list) {
            dtos.add(UserMapper.toUserDTO(users));
        }
        return dtos;
    }
    @Override
    public void addCourses(int userId, int coursesId){
        System.out.println(userId);
        Users user = this.userRepositories.findById(userId).get();
        user.getCourses().add(coursRepositoies.findById(coursesId).get());
        this.userRepositories.save(user);
    }
}
