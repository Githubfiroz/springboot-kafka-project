package lwc.com.springboot;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class WikimediaChangesProducer{

    private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaChangesProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /* Whenever Spring finds single constructor for a Spring Bean
        then Spring will inject kafkaTemplate dependency
        we do not need to include @Autoconfigure for WikimediaChangesProducer constructor
     */
    @Autowired
    public WikimediaChangesProducer(KafkaTemplate<String, String> kafkaTemplate) {

        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage() throws InterruptedException {

        String topic = "wikimedia_recentchange";

        /* To read realtime stream data from wikimedia,
            we use event source and need to add some dependency for the same
            com.launchdarkly.okhttp-eventsource(to read eventsource from wikimedia,
            to read JSON data added bellow 2 dependencies
            com.fasterxml.jackson.core.jackson-core,
            com.fasterxml.jackson.core.jackson-databind
        */

        EventHandler eventHandler = new WikimediaChangesHandler(kafkaTemplate, topic);
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";

        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url));
        // try {
            EventSource eventSource = builder.build();
            eventSource.start();
        //}catch(Exception e){
            //LOGGER.info("Event source Exception WikimediaChangesProducer ->"+e);
        //}
        TimeUnit.MINUTES.sleep(10);

    }
}