package net.engineeringdigest.journalApp.cache;

import net.engineeringdigest.journalApp.entity.ConfigEntry;
import net.engineeringdigest.journalApp.repo.ConfigJournalApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {
    @Autowired
    private ConfigJournalApp configJournalApp;

    public Map<String,String>cacheMap = new HashMap<>();
    public enum keys{
        WEATHER_API;
    }
    @PostConstruct
    public void init() {
        List<ConfigEntry>all = configJournalApp.findAll();
        for(ConfigEntry entry : all) {
            cacheMap.put(entry.getValue(), entry.getKey());
        }
    }

}
