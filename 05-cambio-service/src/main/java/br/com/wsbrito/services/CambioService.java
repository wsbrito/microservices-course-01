package br.com.wsbrito.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wsbrito.CambioRepository;
import br.com.wsbrito.model.Cambio;

@Service
public class CambioService {
	
	@Autowired
	private CambioRepository repository; 
	
	public Cambio findByFromAndTo(String from, String to) {
		return repository.findByFromAndTo(from, to);
	}

	public BigDecimal convertedValue(BigDecimal conversionFactor, BigDecimal amount) {		
		return conversionFactor.multiply(amount);
	}

}
