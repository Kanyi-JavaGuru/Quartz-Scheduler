package co.ke.kanyijavaguru.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class QuartzSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuartzSchedulerApplication.class, args);
	}
}
