package com.movieflix.movieflix.streaming.service;

import com.movieflix.movieflix.category.entity.Category;
import com.movieflix.movieflix.streaming.entity.Streaming;
import com.movieflix.movieflix.streaming.repository.StreamingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StreamingService {

    private final StreamingRepository streamingRepository;

    public List<Streaming> findAll(){
        return streamingRepository.findAll();
    }

    public Streaming saveStreaming(Streaming streaming){
        return streamingRepository.save(streaming);
    }

    public Optional<Streaming> findById(Long id){
        return streamingRepository.findById(id);
    }

    public void deleteStreaming(Long id){
        streamingRepository.deleteById(id);
    }
}
