package com.example.kyrgyzstancentralmedicalcard.dto.response;

import java.util.List;

public record LoginResponse(
        String token,
        List<String> roles
) {}
