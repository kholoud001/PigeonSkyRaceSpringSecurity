package com.pigeonskyracespringsecurity.controller;
import com.pigeonskyracespringsecurity.DTO.PigeonDTO;
import com.pigeonskyracespringsecurity.model.entity.Pigeon;


import com.pigeonskyracespringsecurity.service.PigeonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pigeons")
public class PigeonController {
    private final PigeonService pigeonService;

    @PostMapping(("/add"))
    public ResponseEntity<Pigeon> addPigeon(@Valid @RequestBody PigeonDTO pigeonDTO) throws AccessDeniedException {
        Pigeon pigeon = pigeonService.addPigeonToCompetition(pigeonDTO);
        return ResponseEntity.ok(pigeon);
    }
}
