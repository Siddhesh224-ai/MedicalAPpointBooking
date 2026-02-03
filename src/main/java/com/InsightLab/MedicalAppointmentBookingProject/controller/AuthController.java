package com.InsightLab.MedicalAppointmentBookingProject.controller;

import com.InsightLab.MedicalAppointmentBookingProject.dto.AuthenticationRequest;
import com.InsightLab.MedicalAppointmentBookingProject.dto.AuthenticationResponse;
import com.InsightLab.MedicalAppointmentBookingProject.dto.RegisterRequest;
import com.InsightLab.MedicalAppointmentBookingProject.entity.User;
import com.InsightLab.MedicalAppointmentBookingProject.service.CustomUserDetailsService;
import com.InsightLab.MedicalAppointmentBookingProject.service.UserService;
import com.InsightLab.MedicalAppointmentBookingProject.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        var user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setRole(request.getRole());
        user.setGender(request.getGender());
        user.setDob(request.getDob());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));

        userService.createUser(user);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        final String token = jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(AuthenticationResponse.builder().token(token).build());
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        final String token = jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(AuthenticationResponse.builder().token(token).build());
    }
}
