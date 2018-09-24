package com.lvy.springboot.repository;

import com.lvy.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByAgeGreaterThan(Integer age);

    List<User> findByNameLike(String name);

    @Query(value = "update user set name=?1 where id=?2",nativeQuery = true)
    @Modifying
    int updateById(String name, int id);

    @Query("from User u where u.name=:name")
    User findUser(@Param("name") String name);


}
