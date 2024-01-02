package com.annm.zilliqa_project.repository;

import com.annm.zilliqa_project.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>{
    public Users findByUsername(String username);
}

//
//1	An	Nguyen	$2a$10$zEoT.t3PZZSkG6FbBS4V8uOdxxLq5FVsN8d6aVt8PAjgG29HqnBuK	an123
//        2	Minh An 	Nguyen	$2a$10$qku62u2j4MUL3zg.vctVcuiaOviICfqEMtqrwAvFiOuYLA64YPKDS	an123@gmail.com
//3	Bách 	Trần	$2a$10$wUCF0hBVUd5y0qFwPu/VouWntaZu6SeyzoVgScgXhUdkTwgFqSieS	bach123
//        4	Minh An	Nguyễn	$2a$10$zEoT.t3PZZSkG6FbBS4V8uOdxxLq5FVsN8d6aVt8PAjgG29HqnBuK	AnAdmin

