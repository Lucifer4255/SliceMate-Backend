package com.slicemate.foodservice.clients;

import com.slicemate.foodservice.dto.ImageResponseDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "IMAGE-SERVICE",path = "/images")
public interface ImageServiceClient {

    @PostMapping(value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @CircuitBreaker(name = "imageService" , fallbackMethod = "uploadImageFallback")
    @Retry(name = "ImageService" ,fallbackMethod = "uploadImageFallback")
    public ResponseEntity<ImageResponseDTO> getImageUrl(@RequestParam("file") MultipartFile file);

    default ResponseEntity<ImageResponseDTO> uploadImageFallback(@RequestParam("file") MultipartFile file) {
        return new ResponseEntity<>(new ImageResponseDTO("default:image service down"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
