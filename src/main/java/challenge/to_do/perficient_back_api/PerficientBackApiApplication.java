package challenge.to_do.perficient_back_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan
public class PerficientBackApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(PerficientBackApiApplication.class, args);
    }
}
