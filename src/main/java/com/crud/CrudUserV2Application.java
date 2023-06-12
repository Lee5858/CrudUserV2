package com.crud;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@RestController
public class CrudUserV2Application {


	public static void main(String[] args) {
				SpringApplication.run(CrudUserV2Application.class, args);
	}

	@GetMapping("/secured")
	public String secured(){
		return "This is secured";
	}

	@GetMapping("/public")
	public String pub(){
		return "This is public";
	}

}
