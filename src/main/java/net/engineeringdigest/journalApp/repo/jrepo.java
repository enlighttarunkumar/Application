package net.engineeringdigest.journalApp.repo;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface jrepo extends MongoRepository<JournalEntry, ObjectId> {

}