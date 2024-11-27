package com.practiceapp.practiceapp.Repository;

import com.practiceapp.practiceapp.Entity.Users;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<Users, ObjectId> {

    Users findByUserName(String userName);

    void deleteByUserName(String userName);
}
