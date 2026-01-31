package com.InsightLab.MedicalAppointmentBookingProject.service;


import com.InsightLab.MedicalAppointmentBookingProject.entity.User;
import com.InsightLab.MedicalAppointmentBookingProject.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new com.InsightLab.MedicalAppointmentBookingProject.exception.ResourceNotFoundException("User not found with id: " + id));
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Long id, User userDetails) {
        User user = getUserById(id);

        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setPhone(userDetails.getPhone());
        user.setRole(userDetails.getRole());
        user.setGender(userDetails.getGender());
        user.setDob(userDetails.getDob());
        // Password update logic should ideally be separate or handled carefully (hashing)
        if (userDetails.getPasswordHash() != null && !userDetails.getPasswordHash().isEmpty()) {
             user.setPasswordHash(userDetails.getPasswordHash());
        }

        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }
}
