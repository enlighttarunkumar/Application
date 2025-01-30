package net.engineeringdigest.journalApp.repo;

import net.engineeringdigest.journalApp.entity.ConfigEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalApp extends MongoRepository<ConfigEntry, ObjectId> {


}
