package br.com.wsbrito.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "FooBar endpoint")
@RestController
@RequestMapping("book-service")
public class FooBar {
	
	private Logger logger = LoggerFactory.getLogger(FooBar.class);
	
	@Operation(summary = "End-point for test purpose")
	@GetMapping("/foo-bar")
	/*
	@Retry(
		name = "foo-bar",
		fallbackMethod = "fallbackMethod")
	@CircuitBreaker(
		name = "default",
		fallbackMethod = "fallbackMethod")
	@RateLimiter(name = "default")
	*/
	@Bulkhead(name = "default")
	public String fooBar() {
		logger.info(">>> Request to foo-bar is received <<<");
		//var response = new RestTemplate().getForEntity("http://localhost:8080/foo-bar", String.class);
		//return response.getBody();
		return "Foo-bar!!!";
	}
	
	public String fallbackMethod(Exception ex) {
		logger.info(">>> Running foo-bar fallbackMethod <<<");
		return "fallbackMethod -> foo-bar!!!";
	}

}
