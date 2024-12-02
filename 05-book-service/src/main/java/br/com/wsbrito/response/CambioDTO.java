package br.com.wsbrito.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

public class CambioDTO implements Serializable {

	private static final long serialVersionUID = -7929408781237098229L;
	
	public CambioDTO(Long id, String from, String to, BigDecimal conversionValue, BigDecimal convertedValue,
			String environment) {
		this.id = id;
		this.from = from;
		this.to = to;
		this.conversionFactor = conversionValue;
		this.convertedValue = convertedValue;
		this.environment = environment;
	}
	public CambioDTO() {
	}
	
	private Long id;
	private String from;
	private String to;
	private BigDecimal conversionFactor;
	private BigDecimal convertedValue;
	private String environment;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public BigDecimal getConvertedValue() {
		return convertedValue;
	}
	public void setConvertedValue(BigDecimal convertedValue) {
		this.convertedValue = convertedValue;
	}
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CambioDTO other = (CambioDTO) obj;
		return Objects.equals(id, other.id);
	}
	public BigDecimal getConversionFactor() {
		return conversionFactor;
	}
	public void setConversionFactor(BigDecimal conversionFactor) {
		this.conversionFactor = conversionFactor;
	}
	

}
