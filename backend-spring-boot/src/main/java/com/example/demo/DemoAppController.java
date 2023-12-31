package com.example.demo;

import com.example.demo.service.MessageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/message")
@AllArgsConstructor
@Slf4j
public class DemoAppController {

    private final MessageService messageService;

    @GetMapping("/welcome")
    public ResponseEntity<WelcomeDto> welcomeMessage() {
        log.info("Welcome message controller");
        return new ResponseEntity<WelcomeDto>(new WelcomeDto(messageService.getMessages()),
                HttpStatus.OK);
    }
}
