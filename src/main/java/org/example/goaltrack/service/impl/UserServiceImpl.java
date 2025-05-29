package org.example.goaltrack.service.impl;

import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.goaltrack.common.UserStatus;
import org.example.goaltrack.controller.request.UserCreationRequest;
import org.example.goaltrack.controller.request.UserPasswordRequest;
import org.example.goaltrack.controller.request.UserUpdateRequest;
import org.example.goaltrack.controller.response.UserPageResponse;
import org.example.goaltrack.controller.response.UserResponse;
import org.example.goaltrack.exception.InvalidDataException;
import org.example.goaltrack.exception.ResourceNotFoundException;
import org.example.goaltrack.model.user.User;
import org.example.goaltrack.respository.UserRepository;
import org.example.goaltrack.service.EmailService;
import org.example.goaltrack.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Server
@Slf4j(topic = "USER-SERVICE") // Tự động ta logger để ghi log
@RequiredArgsConstructor // Tự động tạo constructor cho các trường final
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Override
    public UserPageResponse findAll(String keyword, String sort, int page, int size) {
        log.info("findAll start");

        // Sorting
        Sort.Order order = new Sort.Order(Sort.Direction.ASC, "id");
        if (StringUtils.hasLength(sort)) {
            Pattern pattern = Pattern.compile("(\\w+?)(:)(.*)"); //username:john_doe
            Matcher matcher = pattern.matcher(sort);
            if (matcher.find()) {
                String columnName = matcher.group(1);

                if (columnName.equals("first_name")) {
                    columnName = "firstName";
                } else if (columnName.equals("last_name")) {
                    columnName = "lastName";
                }

                if (matcher.group(3).equalsIgnoreCase("asc")) {
                    order = new Sort.Order(Sort.Direction.ASC, columnName);
                } else {
                    order = new Sort.Order(Sort.Direction.DESC, columnName);
                }
            }
        }

        // Xu ly truong hop FE muon bat dau voi page = 1
        int pageNo = 0;
        if (page < 0) throw new InvalidDataException("Page is invalid");
        if (size <= 0) throw new InvalidDataException("Size must be greater than 0");
        if (page > 0) {
            pageNo = page - 1;
        }

        // Paging
        Pageable pageable = PageRequest.of(pageNo, size, Sort.by(order));

        Page<User> entityPage;

        log.info("findAll start");
        if (StringUtils.hasLength(keyword)) {
            entityPage = userRepository.searchByKeyword(keyword, pageable);

        } else {
           entityPage = userRepository.findAll(pageable);
        }

        // return: page no, page size, list
        return getUserPageResponse(page, size, entityPage);
    }

    @Override
    public UserResponse findById(Long id) {
        log.info("Find user by id: {}", id);

        User user = getUserById(id);
        return UserResponse.builder()
                .id(id)
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .gender(user.getGender())
                .dateOfBirth(user.getDateOfBirth())
                .username(user.getUsername())
                .phone(user.getPhone())
                .email(user.getEmail())
                .build();
    }

    @Override
    public UserResponse findByUsername(String username) {
        return null;
    }

    @Override
    public UserResponse findByEmail(String email) {
        return null;
    }

    @Override
    public Long save(UserCreationRequest req) {
        log.info("Saving user: {}", req);

        User userByEmail = userRepository.findByEmail(req.getEmail());
        if (userByEmail != null) {
            throw new InvalidDataException("Email already exists");
        }

        User user = new User();
        user.setFirstName(req.getFirstName());
        user.setLastName(req.getLastName());
        user.setGender(req.getGender());
        user.setDateOfBirth(req.getDateOfBirth());



        user.setPhone(req.getPhone());
        user.setEmail(req.getEmail());
        user.setUsername(req.getUsername());
        user.setType(req.getType());
        user.setStatus(UserStatus.NONE);
        user.setIsActive(true);
        user.setLastLogin(null);
        userRepository.save(user);
        log.info("Saved user: {}", req);

//        try {
//            User savedUser = userRepository.save(user);
//            log.info("User created with ID: {}", savedUser.getId());
//            return savedUser.getId();
//        } catch (Exception e) {
//            log.error("Failed to create user", e);
//            throw new RuntimeException("Failed to create user", e);
//        }

        // send email
        try {
            emailService.emailVerification(req.getEmail(), req.getUsername());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        User savedUser = userRepository.save(user);
        log.info("User created with ID: {}", savedUser.getId());
        return savedUser.getId();
    }

    @Override
    public void update(UserUpdateRequest req) {
        log.info("Updating user: {}", req);

        User user = getUserById(req.getId());
        user.setFirstName(req.getFirstName());
        user.setLastName(req.getLastName());
        user.setGender(req.getGender());
        user.setDateOfBirth(req.getDateOfBirth());
        user.setPhone(req.getPhone());
        user.setEmail(req.getEmail());
        user.setUsername(req.getUsername());
        userRepository.save(user);
        log.info("Updated user: {}", req);
    }

    @Override
    public void changePassword(UserPasswordRequest req) {
        log.info("Changing password for user: {}", req);

        // Get user by id
        User user = getUserById(req.getId());
        if (req.getPassword().equals(req.getConfirmPassword())) {
            user.setPassword(passwordEncoder.encode(req.getPassword())); // Mã hóa
        }

        userRepository.save(user);
        log.info("Changed password for user: {}", user);
    }

    @Override
    public void delete(Long id) {

    }

    private User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    /**
     * Convert User Entity to User Response
     * @param page
     * @param size
     * @param users
     * @return
     */
    private static UserPageResponse getUserPageResponse(int page, int size, Page<User> users) {
        log.info("Convert User Entity Page");
        List<UserResponse> userList = users.stream().map(entity -> UserResponse.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .gender(entity.getGender())
                .dateOfBirth(entity.getDateOfBirth())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .build()
        ).toList();

        UserPageResponse response = new UserPageResponse();
        response.setPageNumber(page);
        response.setPageSize(size);
        response.setTotalElements(users.getTotalElements());
        response.setTotalPages(users.getTotalPages());
        response.setUsers(userList);
        return response;
    }
}
