package com.example.kyrgyzstancentralmedicalcard.controller;

import com.example.kyrgyzstancentralmedicalcard.services.QrCodeService;
import com.example.kyrgyzstancentralmedicalcard.services.UserService;
import com.google.zxing.WriterException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/qrcode")
@RequiredArgsConstructor
public class QrCodeController {
    private final QrCodeService qrCodeService;
    private final UserService userService;
    @Value("${app.base-url}")
    private String baseUrl;


    @GetMapping("/qr-code/users/{id}")
    public ResponseEntity<byte[]> getUserQrCode(@PathVariable Long id){
        userService.getById(id);

        String userUrl = baseUrl + "/api/users/get-by-id/" + id;
        try{
            byte[] qrcodeImage = qrCodeService.generateQrCodeImage(userUrl, 250, 250);

            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(qrcodeImage);
        }catch (WriterException | IOException e){
            return ResponseEntity.internalServerError().build();
        }
    }
}
