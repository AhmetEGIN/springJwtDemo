package com.example.springJwtDemo.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/demo")
@RequiredArgsConstructor
public class DemoController {
	
	@GetMapping("/sayhello")
	public ResponseEntity<String> sayHello(){
		return ResponseEntity.ok("Hello World");
	}
	
}
