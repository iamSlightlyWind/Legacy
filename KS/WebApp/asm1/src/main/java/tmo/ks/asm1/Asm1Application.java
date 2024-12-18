package tmo.ks.asm1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class Asm1Application {

	public static void main(String[] args) {
		SpringApplication.run(Asm1Application.class, args);
	}

}
