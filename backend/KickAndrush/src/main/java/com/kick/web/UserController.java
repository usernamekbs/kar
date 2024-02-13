package com.kick.web;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kick.service.UserService;
import com.kick.web.dto.user.UserDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
	 
	private final UserService userService;

	@PostMapping("/create") 
    public void create(@RequestBody UserDto userDto) {
		userService.create(userDto);
	}
	
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@GetMapping("/list")
	public List<UserDto> list() {
		return userService.list();
	}
	
	@GetMapping("/view/{id}")
	public UserDto view(@PathVariable long id) {
		return userService.view(id);
	}
	
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@PutMapping("/update/{id}")
	public UserDto update(@RequestBody UserDto userDto,@PathVariable long id) {
		return userService.update(id,userDto);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable long id) {
		userService.delete(id);
	}
	
}