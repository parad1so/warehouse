package ru.seoweblab.dao.spring;

import ru.seoweblab.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserAccount, Integer> {
    UserAccount findByName(String name);
}
