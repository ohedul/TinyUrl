package com.ohedul.demourlshortener.DemoUrlShortener.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collection;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ohedul.demourlshortener.DemoUrlShortener.model.Url;
import com.ohedul.demourlshortener.DemoUrlShortener.repository.UrlRepository;


@RestController
@RequestMapping("/api")
public class ShortUrlController {
	private final Logger log = LoggerFactory.getLogger(ShortUrlController.class);
	private final Set<String> allUrls;
	private final UrlRepository repository;
	
	public ShortUrlController(UrlRepository repository) {
		this.repository = repository;
		allUrls = new HashSet<String> ();
	}
	
	
	@GetMapping("/urlshortner")
	ResponseEntity findAllTinyUrl(){
		return ResponseEntity.ok().body(repository.findAll());
	}
	
	@GetMapping("/urlshortner/{key}")
	ResponseEntity findAByKey(@PathVariable("key") String key){
		try {
			Url url = repository.findByKey(key);
            return ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT).location(new URL(url.getLongUrl()).toURI())
                    .build();

        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("mal formed Exception");
        }
	}
	
	@PostMapping("/urlshortner")
	ResponseEntity<Collection<Url>> saveTinyUrl(@RequestBody String requestBody){
		repository.findAll().forEach(url->allUrls.add(url.getLongUrl()));
		if(allUrls.contains(requestBody)) {
			return ResponseEntity.ok().body(repository.findAll());
		}
		if(isValidUrl(requestBody)) {
			try {
				Url url = new Url();
				url.setLongUrl(requestBody);
				repository.save(url);
				return ResponseEntity.ok().body(repository.findAll());
			}catch(Exception e) {
				log.info(e.getMessage());
			}
			return ResponseEntity.badRequest().body(null);
		}else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	private boolean isValidUrl(String longUrl) {
		boolean isValid = false;
		try {
			final URLConnection connection = new URL(longUrl).openConnection();
			connection.connect();
			isValid = true;
			connection.setConnectTimeout(10000);
		}catch(final MalformedURLException e) {
			return false;
		}
		catch(final IOException e) {
			return false;
		}
		return isValid;
	}

}
