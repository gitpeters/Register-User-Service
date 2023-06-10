package com.peters.User_Registration_and_Email_Verification.user.controller;

import com.peters.User_Registration_and_Email_Verification.user.dto.CustomResponse;
import com.peters.User_Registration_and_Email_Verification.user.dto.LoginRequestDto;
import com.peters.User_Registration_and_Email_Verification.user.dto.UserRequestDto;
import com.peters.User_Registration_and_Email_Verification.user.service.IUserService;
import com.peters.User_Registration_and_Email_Verification.user.service.UserAuthenticationService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("api/v1/register")
@RequiredArgsConstructor
public class NonAuthController {
    private final IUserService userService;
    private final UserAuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<CustomResponse> register(@RequestBody UserRequestDto requestDto){
        return userService.registerUser(requestDto);
    }

    @GetMapping("/verify-email")
    public ResponseEntity<CustomResponse> verifyEmail(@RequestParam("token") String token){
        return userService.verifyEmail(token);
    }

    @GetMapping("/resend-token")
    public ResponseEntity<?> resendVerificationToken(@RequestParam("token") String oldToken) throws MessagingException, UnsupportedEncodingException {
      return userService.resendVerificationTokenEmail(oldToken);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<CustomResponse> authenticateUser(@RequestBody @Validated LoginRequestDto loginRequest) throws Exception {
        return authenticationService.createAuthenticationTokenAndAuthenticateUser(loginRequest);
    }


}