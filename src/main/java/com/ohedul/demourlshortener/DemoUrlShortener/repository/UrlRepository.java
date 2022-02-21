package com.ohedul.demourlshortener.DemoUrlShortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ohedul.demourlshortener.DemoUrlShortener.model.Url;


public interface UrlRepository extends JpaRepository<Url, Long> {
		public Url findByKey(String key);
		public Url findByLongUrl(String longUrl);
}
