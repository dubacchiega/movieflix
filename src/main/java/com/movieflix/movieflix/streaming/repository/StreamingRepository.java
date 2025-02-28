package com.movieflix.movieflix.streaming.repository;

import com.movieflix.movieflix.streaming.entity.Streaming;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StreamingRepository extends JpaRepository<Streaming, Long> {
}
