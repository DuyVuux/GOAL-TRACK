package org.example.goaltrack.service;

import org.example.goaltrack.controller.request.UserCreationRequest;
import org.example.goaltrack.controller.request.UserPasswordRequest;
import org.example.goaltrack.controller.request.UserUpdateRequest;
import org.example.goaltrack.controller.response.UserResponse;

import java.util.List;

public interface UserService {

    List<UserResponse> findAll(String keyword, String sort, int page, int size);

    UserResponse findById(Long id);

    UserResponse findByUsername(String username);

    UserResponse findByEmail(String email);

    Long save(UserCreationRequest req);

    void update(UserUpdateRequest req);

    void changePassword(UserPasswordRequest req);

    void delete(Long id);
}
