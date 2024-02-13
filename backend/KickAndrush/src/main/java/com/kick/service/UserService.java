package com.kick.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kick.entity.ERole;
import com.kick.entity.Role;
import com.kick.entity.User;
import com.kick.repository.role.RoleRepository;
import com.kick.repository.user.UserRepository;
import com.kick.web.dto.user.UserDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService{
	 
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Transactional(readOnly = true)
	public List<UserDto> list() {
		List<User> user = userRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
		return user.stream().map(UserDto::new).collect(Collectors.toList());
	}
	
	public void create(UserDto userDto) {
		if (userRepository.existsByUsername(userDto.getUsername())) {
			System.out.println("유저네임을 이미 사용중입니다.");
	    }

		if (userRepository.existsByEmail(userDto.getEmail())) {
			System.out.println("Error: 이메일을 이미 사용중입니다.");
	    }
		User user = User.builder()
						.username(userDto.getUsername())
						.password(passwordEncoder.encode(userDto.getPassword()))
						.nickname(userDto.getNickname())
						.email(userDto.getEmail())
						.build();
		
		Role role = roleRepository.findByName(ERole.USER);
		user.addRole(role);
		userRepository.save(user);
	}

	@Transactional(readOnly = true)
	public UserDto view(long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. id=" + id));
		return new UserDto(user);
	}
	
	public UserDto update(long id, UserDto userDto) {
		User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. userId=" + id));
		user.updateUser(userDto.getUsername(), userDto.getNickname(), userDto.getEmail());
		return new UserDto(user);
	}

	public void delete(long id) {
		userRepository.deleteById(id);
	}

}

