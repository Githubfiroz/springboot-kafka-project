package lwc.com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(scanBasePackages = "lwc.com.springboot")
@SpringBootApplication
public class  SpringBootConsumerApplication {

    public static void main(String[] args) {
        //System.out.println(" *********************************** Started SpringBootConsumerApplication ***********************************");
        SpringApplication.run(SpringBootConsumerApplication.class);
        //SpringApplication.run(SpringBootConsumerApplication.class, args);
        System.out.println(" *********************************** End SpringBootConsumerApplication ***********************************");
    }
}  