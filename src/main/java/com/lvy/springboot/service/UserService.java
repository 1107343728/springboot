package com.lvy.springboot.service;

import com.lvy.springboot.entity.User;
import com.lvy.springboot.vo.UserVo;

import java.util.Iterator;
import java.util.List;

public interface UserService {
    public void insert(User user);
    public void add(UserVo uservo);
    public int update(User user);
    public void delete(Long id);
    public User selectById(Long id);
    public Iterator<User> selectAll(int pageNum, int pageSize);
    User findByAgeGreaterThan(Integer age);
    List<User> findByNameLike(String name);
}
