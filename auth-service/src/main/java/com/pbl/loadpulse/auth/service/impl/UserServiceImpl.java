package com.pbl.loadpulse.auth.service.impl;

import com.pbl.loadpulse.auth.domain.User;
import com.pbl.loadpulse.auth.mapper.UserMapper;
import com.pbl.loadpulse.auth.payload.request.SignUpRequest;
import com.pbl.loadpulse.auth.payload.response.UserInfoResponse;
import com.pbl.loadpulse.auth.repository.UserRepository;
import com.pbl.loadpulse.auth.service.UserService;
import com.pbl.loadpulse.common.common.CommonFunction;
import com.pbl.loadpulse.common.constant.MessageConstant;
import com.pbl.loadpulse.common.exception.BadRequestException;
import com.pbl.loadpulse.email.service.EmailService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  private final RedisTemplate<String, Object> redisTemplate;

  private final EmailService emailService;

  private final UserMapper userMapper;

  @Override
  @Transactional
  public UserInfoResponse signUp(SignUpRequest signUpRequest) {
    User user = new User();

    if (this.isEmailExist(signUpRequest.getEmail())) {
      throw new BadRequestException(MessageConstant.EMAIL_ALREADY_EXISTS);
    }

    if (!this.isPasswordMatch(signUpRequest.getPassword(), signUpRequest.getConfirmPassword())) {
      throw new BadRequestException(MessageConstant.PASSWORD_NOT_MATCHED_WITH_CONFIRM_PASSWORD);
    }

    user.setEmail(signUpRequest.getEmail());
    user.setPassword(signUpRequest.getPassword());
    userRepository.save(user);

    UUID confirmToken = CommonFunction.generateUUID();
    this.setConfirmationTokenToRedis(user, confirmToken);
    emailService.sendMailConfirmRegister(user.getEmail(), confirmToken);

    return userMapper.toUserInfoResponse(user);
  }

  private boolean isEmailExist(String email) {
    return userRepository.existsByEmail(email);
  }

  private boolean isPasswordMatch(String password, String confirmPassword) {
    return password.equals(confirmPassword);
  }

  private void setConfirmationTokenToRedis(User user, UUID token) {
    redisTemplate
        .opsForValue()
        .set(String.format("%s:confirmToken", user.getEmail()), token, Duration.ofMinutes(5));
  }
}
