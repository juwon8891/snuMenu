package com.example.snufoodmenu.controller;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SlackChallenge {
    private String type; // url_verification
    private String token; // 토큰
    private String challenge; // test 값
}