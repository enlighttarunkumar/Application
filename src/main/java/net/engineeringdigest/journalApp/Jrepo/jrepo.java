package net.engineeringdigest.journalApp.Jrepo;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

public interface jrepo extends MongoRepository<JournalEntry, ObjectId> {

}
