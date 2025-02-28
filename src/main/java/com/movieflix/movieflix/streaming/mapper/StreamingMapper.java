package com.movieflix.movieflix.streaming.mapper;

import com.movieflix.movieflix.streaming.entity.Streaming;
import com.movieflix.movieflix.streaming.request.StreamingRequest;
import com.movieflix.movieflix.streaming.response.StreamingResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StreamingMapper {

    public static Streaming toStreaming(StreamingRequest streamingRequest){
        return Streaming.builder()
                .name(streamingRequest.name())
                .build();
    }

    public static StreamingResponse toStreamingResponse(Streaming streaming){
        return StreamingResponse.builder()
                .id(streaming.getId())
                .name(streaming.getName())
                .build();
    }
}
