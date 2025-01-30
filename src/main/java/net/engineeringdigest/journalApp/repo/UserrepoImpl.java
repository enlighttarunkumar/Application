package net.engineeringdigest.journalApp.repo;

import net.engineeringdigest.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class UserrepoImpl {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> findUsingSentiment() {
        Query query = new Query();
        query.addCriteria(Criteria.where("sentiment").is(true));
        query.addCriteria(Criteria.where("email").regex("[a-zA-Z0-9.*%±]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,6}"));
        List<User> user = mongoTemplate.find(query, User.class);
        return user;
    }

}
