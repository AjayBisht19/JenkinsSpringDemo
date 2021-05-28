package com.medicare;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.medicare.dto.MailRequest;
import com.medicare.dto.MailResponse;
import com.medicare.service.EmailService;

@SpringBootApplication
@RestController
public class MedicareBackend1Application {
	@Autowired
	private EmailService service;
	
	@PostMapping("/sendingEmail")
	public MailResponse sendEmail(@RequestBody MailRequest request) {
		Map<String,Object> model=new HashMap<>();
		model.put("location", "bangalore");
		model.put("Name", request.getName());
		return service.sendEmail(request, model);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MedicareBackend1Application.class, args);
	}

}
