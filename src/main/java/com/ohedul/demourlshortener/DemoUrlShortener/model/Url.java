package com.ohedul.demourlshortener.DemoUrlShortener.model;

import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tiny_url")
public class Url {
	
	@Id
	@GeneratedValue
	private Long id;
	

	private String longUrl;
	
	@Column(unique=true)
	private String key;
	
	
	public String getLongUrl() {
		return longUrl;
	}
	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
		this.key = getKeyString();
	}
	public String getKey() {
		return key;
	}
	
	
	private String getKeyString() {
		String characterSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ_abcdefghijklmnopqrstuvwxyz1234567890";
	    StringBuilder sb = new StringBuilder();

	    for (int i = 0; i < 8; i++) {
	        int index = new Random().nextInt(characterSet.length());
	        sb.append(characterSet.charAt(index));
	    }

	    String keyCode = sb.toString();
	    return keyCode;
	}

}
