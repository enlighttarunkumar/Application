package net.engineeringdigest.journalApp.repo;

import net.engineeringdigest.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Userrepo extends MongoRepository<User, ObjectId> {
    User findByUsername(String username);
}