package com.utp.ms_auth.controller;

import com.utp.ms_auth.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/internal")
@RequiredArgsConstructor
@Tag(name = "Internal", description = "Llamadas servicio-a-servicio (no para el frontend)")
public class InternalController {

    private final UserRepository userRepository;

    @Value("${internal.api-key}")
    private String internalApiKey;

    @GetMapping("/emails")
    @Operation(summary = "Listar todos los correos registrados (uso interno de ms-notifications)")
    public ResponseEntity<List<String>> allEmails(@RequestHeader("X-Internal-Key") String key) {
        if (!internalApiKey.equals(key)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(userRepository.findAllEmails());
    }
}