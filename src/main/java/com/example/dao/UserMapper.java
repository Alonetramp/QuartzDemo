package com.example.dao;

import com.example.core.Mapper;
import com.example.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends Mapper<User> {
}