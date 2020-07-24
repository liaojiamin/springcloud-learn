package com.ljm.springcloud.repository;

import com.ljm.springcloud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author liaojiamin
 * @Date:Created in 14:47 2020/7/6
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
