package cn.meeler.test.providerhi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ProviderHiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderHiApplication.class, args);
    }

}
