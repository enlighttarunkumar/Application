package net.engineeringdigest.journalApp.Schedulers;

import net.engineeringdigest.journalApp.Journalservice.EmailService;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.enums.Sentiment;
import net.engineeringdigest.journalApp.repo.UserrepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Component
public class UserScheduler {
    @Autowired
    private UserrepoImpl userrepo;
    @Autowired
    private EmailService emailService;


    public void fetchUserAndSendEmail() {
        List<User>users = userrepo.findUsingSentiment();
        for (User user : users) {
            List<JournalEntry>journalEntries = user.getUserJE();
            List<Sentiment>sentiments = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiment()).collect(Collectors.toList());
            Map<Sentiment, Integer> sentimentMap = new HashMap<>();
            for(Sentiment sentiment : sentiments) {
                if(sentiment != null) {
                    sentimentMap.put(sentiment,sentimentMap.getOrDefault(sentiment,0)+1);
                }
            }
            Sentiment sentimentx = null;int maxCount = 0;
            for (Map.Entry<Sentiment, Integer> entry : sentimentMap.entrySet()) {
                if(entry.getValue() > maxCount) {
                    maxCount = entry.getValue();
                    sentimentx = entry.getKey();
                }
            }
            if(sentimentx != null) {
                emailService.sendEmail(user.getEmail(), "Sentiment for Last Seven Days : ", sentimentx.toString());
            }
        }
    }
}
