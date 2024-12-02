package br.com.wsbrito.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;

@Entity(name = "book")
@JsonIgnoreProperties({
	"hibernateLazyInitializer",
	"handler"
})
public class Book implements Serializable {

	public Book(
			Long id, 
			String author, 
			String title, 
			Date launchDate, 
			BigDecimal price, 
			String currency,
			String environment) {
		this.id = id;
		this.author = author;
		this.launchDate = launchDate;
		this.price = price;
		this.title = title;
		this.currency = currency;
		this.environment = environment;
	}

	private static final long serialVersionUID = 4386228500668803545L;
	
	public Book() {
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(
			name = "author", 
			nullable = false,
			length = 180)	
	private String author;
	
	@Column(
			name = "launch_date", 
			nullable = false,
			length = 180)
	@Temporal(TemporalType.DATE)
	private Date launchDate;
	
	@Column(nullable = false)
	private BigDecimal price;
	
	@Column(
			name = "title", 
			nullable = false,
			length = 250)	
	private String title;
	
	@Transient
	private String currency;
	
	@Transient
	private String environment;

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
		Book other = (Book) obj;
		return Objects.equals(id, other.id);
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getLaunchDate() {
		return launchDate;
	}
	public void setLaunchDate(Date launchDate) {
		this.launchDate = launchDate;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}

}
