package com.lvy.springboot.service;

import com.lvy.springboot.entity.User;
import com.lvy.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public void insert(User user) {
        userRepository.save(user);
    }

    @Override
    public int update(User user) {
        userRepository.save(user);
        return 1;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User selectById(Long id) {
        Optional<User> optional = userRepository.findById(id);
        User user = optional.get();
       return user;
    }

    @Override
    public Iterator<User> selectAll(int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(pageNum, pageSize, sort);
        Page<User> users = userRepository.findAll(pageable);
        Iterator<User> userIterator = users.iterator();
        return userIterator;
    }

    @Override
    public User findByAgeGreaterThan(Integer age) {
        return userRepository.findByAgeGreaterThan(age);
    }

    @Override
    public List<User> findByNameLike(String name) {
        return userRepository.findByNameIsLike(name);
    }
}
