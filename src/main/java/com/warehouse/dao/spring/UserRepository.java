package com.warehouse.dao.spring;

import com.warehouse.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserAccount, Integer> {
    UserAccount findByName(String name);
}
