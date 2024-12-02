package br.com.wsbrito.controllers;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wsbrito.model.Cambio;
import br.com.wsbrito.services.CambioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Cambio endpoint")
@RestController
@RequestMapping("cambio-service")
public class CambioController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private CambioService service;
	
	@Operation(summary = "Get Cambio from currency")
	@GetMapping("/{amount}/{from}/{to}")
	public Cambio getCambio(
			@PathVariable BigDecimal amount,
			@PathVariable String from,
			@PathVariable String to
			
	) {
		
		var cambio = service.findByFromAndTo(from, to);
		if(cambio == null) {
			throw new RuntimeException("Currency is not found");
		}
		
		var port = environment.getProperty("local.server.port");
		cambio.setEnvironment(port);
		
		var convertedValue = service.convertedValue(cambio.getConversionFactor(), amount);
		cambio.setConvertedValue(convertedValue.setScale(2, RoundingMode.CEILING));
		
		return cambio;
	}

}
