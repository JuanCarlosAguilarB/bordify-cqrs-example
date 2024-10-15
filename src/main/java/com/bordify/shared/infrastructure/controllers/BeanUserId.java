//package com.bordify.shared.infrastructure.controllers;
//
//import com.bordify.auth.domain.AuthServices;
//import com.bordify.auth.domain.TokenUserResponse;
//import com.bordify.auth.domain.UserWriteModel;
//import com.bordify.shared.domain.UserUserId;
//import lombok.AllArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@AllArgsConstructor
//public class BeanUserId {
//
//    private final AuthServices authServices;
//    private final GetTokenFromRequest getTokenFromRequestService;
//
//    @Bean
//    public UserUserId getUserId() {
//
//        String token = getTokenFromRequestService.getToken();
//
//        if (token == null) {
//            return null;
//        }
//
//        UserWriteModel user = authServices.decode(
//                TokenUserResponse.builder().token(token).build()
//        );
//
//        return user.id();
//    }
//
//
//}
//
//
