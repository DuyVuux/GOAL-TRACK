//package org.example.goaltrack.controller;
//
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.validation.Valid;
//import jakarta.validation.constraints.Min;
//import lombok.extern.slf4j.Slf4j;
//import org.example.goaltrack.common.Gender;
//import org.example.goaltrack.controller.request.UserCreationRequest;
//import org.example.goaltrack.controller.request.UserPasswordRequest;
//import org.example.goaltrack.controller.request.UserUpdateRequest;
//import org.example.goaltrack.controller.response.UserPageResponse;
//import org.example.goaltrack.controller.response.UserResponse;
//import org.example.goaltrack.service.impl.UserServiceImpl;
//import org.hibernate.annotations.Filter;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.IOException;
//import java.util.Date;
//import java.util.LinkedHashMap;
//
//import java.util.Map;
//
//@RestController
//@RequestMapping("/user")
//@Tag(name = "USER-CONTROLLER")
//@Slf4j(topic = "UserController")
//@Validated
//public class UserController {
//
//    private final UserServiceImpl userService;
//
//    public UserController(UserServiceImpl userServiceImpl) {
//        this.userService = userServiceImpl;
//    }
//
//    @Operation(summary = "Get user list", description = "API retrieve user from database")
//    @GetMapping("/list")
//    public Map<String, Object> getList(@RequestParam(required = false) String keyword,
//                                       @RequestParam(required = false) String sort,
//                                       @RequestParam(defaultValue = "0") int page,
//                                       @RequestParam(defaultValue = "20") int size) {
//        log.info("Get user list");
//
//        Map<String, Object> result = new LinkedHashMap<>();
//        result.put("status", HttpStatus.OK.value());
//        result.put("message", "user list");
//        result.put("data", userService.findAll(keyword, sort, page, size));
//
//        return result;
//    }
//
//    @Operation(summary = "Get User detail", description = "API retrieve user detail by ID from database")
//    @GetMapping("/{userId}")
//    public Map<String, Object> getUserDetail(@PathVariable @Min(value = 1, message = "userId must be equals or greater than 1") Long userId) {
//
//        log.info("Get user detail by ID: {}", userId);
//
//        UserResponse userDetail = userService.findById(userId);
////         Return a user by ID
//        userDetail.setId(1L);
//        userDetail.setFirstName("Duke");
//        userDetail.setLastName("Shelby");
//        userDetail.setGender(Gender.MALE);
//        userDetail.setDateOfBirth(new Date());
//        userDetail.setUsername("admin");
//        userDetail.setEmail("admin@gmail.com");
//        userDetail.setPhone("0353826992");
//
//        Map<String, Object> result = new LinkedHashMap<>();
//        result.put("status", HttpStatus.OK.value());
//        result.put("message", "user list");
//        result.put("data", userDetail);
//
//        return result;
//    }
//
//    @Operation(summary = "Create User", description = "API add new user to database")
//    @PostMapping("/add")
//    public ResponseEntity<Object> createUser(@RequestBody @Valid UserCreationRequest request) {
//        log.info("Create user: {}", request);
//
//        Map<String, Object> result = new LinkedHashMap<>();
//        result.put("status", HttpStatus.CREATED.value());
//        result.put("message", "User created successfully");
//        result.put("data", userService.save(request));
//        return new ResponseEntity<>(result, HttpStatus.CREATED);
//    }
//
//    @Operation(summary = "Update User", description = "API update user to database")
//    @PutMapping("/upd")
//    public Map<String, Object> updateUser(@RequestBody @Valid UserUpdateRequest request) {
//        log.info("Updating user: {}", request);
//
//        userService.update(request);
//
//        Map<String, Object> result = new LinkedHashMap<>();
//        result.put("status", HttpStatus.ACCEPTED.value());
//        result.put("message", "User updated successfully");
//        result.put("data", "");
//
//        return result;
//    }
//
//    @Operation(summary = "Change Password", description = "API change password for user to database")
//    @PatchMapping("/change-pwd")
//    public Map<String, Object> changePassword(@RequestBody @Valid UserPasswordRequest request) {
//        log.info("Changing password for user: {}", request);
//
//        userService.changePassword(request);
//
//        Map<String, Object> result = new LinkedHashMap<>();
//        result.put("status", HttpStatus.NO_CONTENT.value());
//        result.put("message", "Password updated successfully");
//        result.put("data", "");
//
//        return result;
//    }
//
//    @GetMapping("/confirm-email")
//    public void confirmEmail(@RequestParam String secretCode, HttpServletResponse response) throws IOException {
//        log.info("Confirm email: {}", secretCode);
//        try {
//            // TODO check or compare secretCode from database
//        } catch (Exception e) {
//            log.error("Confirm email was failure!, errorMessage={}", e.getMessage());
//        } finally {
//            response.sendRedirect("https://dukeshyjava.vn/wp-admin");
//        }
//    }
//
//    @Operation(summary = "Delete user", description = "API activate user from database")
//    @DeleteMapping("/del/{userId}")
//    public Map<String, Object> deleteUser(@PathVariable  @Min(value = 1, message = "userId must be equals or greater than 1") Long userId) {
//        log.info("Deleting user: {}", userId);
//
//        userService.delete(userId);
//
//        Map<String, Object> result = new LinkedHashMap<>();
//        result.put("status", HttpStatus.RESET_CONTENT.value());
//        result.put("message", "User deleted successfully");
//        result.put("data", "");
//
//        return result;
//    }
//}
package org.example.goaltrack.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.example.goaltrack.common.Gender;
import org.example.goaltrack.controller.request.UserCreationRequest;
import org.example.goaltrack.controller.request.UserPasswordRequest;
import org.example.goaltrack.controller.request.UserUpdateRequest;
import org.example.goaltrack.controller.response.UserPageResponse;
import org.example.goaltrack.controller.response.UserResponse;
import org.example.goaltrack.respository.UserRepository;
import org.example.goaltrack.model.user.User;
import org.example.goaltrack.service.impl.UserServiceImpl;
import org.hibernate.annotations.Filter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;

import java.util.Map;

@RestController
@RequestMapping("/user")
@Tag(name = "USER-CONTROLLER")
@Slf4j(topic = "UserController")
@Validated
public class UserController {
    private final UserRepository userRepository;

    private final UserServiceImpl userService;

    @Operation(summary = "Get user list", description = "API retrieve user from database")
    @GetMapping("/list")
    public Map<String, Object> getList(@RequestParam(required = false) String keyword,
                                       @RequestParam(required = false) String sort,
                                       @RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "20") int size) {
        log.info("Get user list");

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("status", HttpStatus.OK.value());
        result.put("message", "user list");
        result.put("data", userService.findAll(keyword, sort, page, size));

        return result;
    }

    @Operation(summary = "Get User detail", description = "API retrieve user detail by ID from database")
    @GetMapping("/{userId}")
    public Map<String, Object> getUserDetail(@PathVariable @Min(value = 1, message = "userId must be equals or greater than 1") Long userId) {

        log.info("Get user detail by ID: {}", userId);

        UserResponse userDetail = userService.findById(userId);
//         Return a user by ID
        userDetail.setId(1L);
        userDetail.setFirstName("Duke");
        userDetail.setLastName("Shelby");
        userDetail.setGender(Gender.MALE);
        userDetail.setDateOfBirth(new Date());
        userDetail.setUsername("admin");
        userDetail.setEmail("admin@gmail.com");
        userDetail.setPhone("0353826992");

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("status", HttpStatus.OK.value());
        result.put("message", "user list");
        result.put("data", userDetail);

        return result;
    }

    @Operation(summary = "Create User", description = "API add new user to database")
    @PostMapping("/add")
    public ResponseEntity<Object> createUser(@RequestBody @Valid UserCreationRequest request) {
        log.info("Create user: {}", request);

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("status", HttpStatus.CREATED.value());
        result.put("message", "User created successfully");
        result.put("data", userService.save(request));
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @Operation(summary = "Update User", description = "API update user to database")
    @PutMapping("/upd")
    public Map<String, Object> updateUser(@RequestBody @Valid UserUpdateRequest request) {
        log.info("Updating user: {}", request);

        userService.update(request);

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("status", HttpStatus.ACCEPTED.value());
        result.put("message", "User updated successfully");
        result.put("data", "");

        return result;
    }

    @Operation(summary = "Change Password", description = "API change password for user to database")
    @PatchMapping("/change-pwd")
    public Map<String, Object> changePassword(@RequestBody @Valid UserPasswordRequest request) {
        log.info("Changing password for user: {}", request);

        userService.changePassword(request);

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("status", HttpStatus.NO_CONTENT.value());
        result.put("message", "Password updated successfully");
        result.put("data", "");

        return result;
    }

    public UserController(UserRepository userRepository, UserServiceImpl userServiceImpl) {
        this.userRepository = userRepository;
        this.userService = userServiceImpl;
    }

    @GetMapping("/confirm-email")
    public void confirmEmail(@RequestParam String secretCode, HttpServletResponse response) throws IOException {
        log.info("Confirm email: {}", secretCode);
        try {
            // Tìm user với secretCode
            User user = userRepository.findBySecretCode(secretCode)
                    .orElseThrow(() -> new RuntimeException("Invalid secret code"));
                // Cập nhật trạng thái xác thực email
                user.setIsEmailVerified(true);
                user.setSecretCode(null); // Xóa secret code
                userRepository.save(user);
                log.info("Email confirmed successfully");
                response.sendRedirect("https://dukeshyjava.vn/wp-admin");
        } catch(Exception e){
            log.error("Confirm email failed: {}", e.getMessage());
            response.sendRedirect("https://dukeshyjava.vn/wp-admin?error=invalid_code");
        }

    }

    @Operation(summary = "Delete user", description = "API activate user from database")
    @DeleteMapping("/del/{userId}")
    public Map<String, Object> deleteUser(@PathVariable  @Min(value = 1, message = "userId must be equals or greater than 1") Long userId) {
        log.info("Deleting user: {}", userId);

        userService.delete(userId);

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("status", HttpStatus.RESET_CONTENT.value());
        result.put("message", "User deleted successfully");
        result.put("data", "");

        return result;
    }
}
