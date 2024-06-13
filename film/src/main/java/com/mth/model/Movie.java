package com.mth.model;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "movies")

public class Movie implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "image", nullable = false)
	private String image;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "release_year", nullable = false)
	private Integer year;

	@Column(name = "rate", nullable = false)
	private Integer rate;

	@Column(name = "num_rate", nullable = false)
	private Integer num_rate;

	@Column(name = "director", nullable = false)
	private String director;

	@Column(name = "certificate", nullable = false)
	private String certificate;

	@Column(name = "metascore", nullable = false)
	private Integer metascore;

	@OneToMany(mappedBy = "movie")
	private Set<Favorites> favorites;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public Integer getNum_rate() {
		return num_rate;
	}

	public void setNum_rate(Integer num_rate) {
		this.num_rate = num_rate;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public Integer getMetascore() {
		return metascore;
	}

	public void setMetascore(Integer metascore) {
		this.metascore = metascore;
	}

}
