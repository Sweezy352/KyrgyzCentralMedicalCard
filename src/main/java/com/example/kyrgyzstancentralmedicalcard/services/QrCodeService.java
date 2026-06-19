package com.example.kyrgyzstancentralmedicalcard.services;

import com.google.zxing.WriterException;

import java.io.IOException;

public interface QrCodeService {
    byte[] generateQrCodeImage(String text, int width, int height) throws WriterException, IOException;

}