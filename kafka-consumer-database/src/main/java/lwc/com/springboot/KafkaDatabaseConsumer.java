package lwc.com.springboot;

import lwc.com.springboot.entity.WikimediaData;
import lwc.com.springboot.repository.WikimediaDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaDatabaseConsumer{
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);

    @Autowired
    private WikimediaDataRepository dataRepository;

    @Autowired
    public KafkaDatabaseConsumer(WikimediaDataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @KafkaListener(topics = "wikimedia_recentchange", groupId = "myGroup")
    public void consumeMessage(String eventMessage){
        System.out.println(eventMessage+" ****** Started consumeMessage ************");
        //LOGGER.info(String.format("Event message received -> %s", eventMessage));
       // try{
            LOGGER.info(String.format("Event message received -> %s", eventMessage));

            WikimediaData wikimediaData = new WikimediaData();
            wikimediaData.setWikiEventData(eventMessage);

            dataRepository.save(wikimediaData);
        /*
        } catch (Exception e) {

            //e.printStackTrace();
            LOGGER.error("********* An error occurred in consumeMessage ********** ", e);

            // You can also log additional context information
            //LOGGER.error("Additional context information: {}", someContextInfo);

        }
        */
        System.out.println(" ******************** End consumeMessage ****************");
    }

}
