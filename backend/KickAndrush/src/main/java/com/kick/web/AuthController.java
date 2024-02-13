package com.kick.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kick.entity.UserDetailsImpl;
import com.kick.filter.JWTGenerator;
import com.kick.web.dto.login.AuthDto;
import com.kick.web.dto.login.LoginDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
	private final AuthenticationManager authenticationManager;
    private final JWTGenerator jwtGenerator;
    
    @PostMapping("/login")
    public ResponseEntity<AuthDto> login(@RequestBody LoginDto loginDto){
        
    	Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(),
                loginDto.getPassword()));
    	
    	//인증 후 최종 인증 결과(user 객체, 권한 정보)를 담고 SecurityContext 에 저장되어 전역적으로 참조가 가능하다.
    	//어떤 메서드에서든 참조가 가능하며 인증객체를 어디서든 꺼낼수있다.
    	SecurityContextHolder.getContext().setAuthentication(authentication);
        //사용자 별 Authentication 인증 객체를 어떻게 구분하는가? 
    	//위 그림에서도 나와있듯이 SecurityContextHolder라는 전역 객체 안에 
    	//SecurityContext에 인증 객체를 저장하는데, 이 SecurityContextHolder는 ThreadLocal에 
    	//저장되기 때문에 각기 다른 쓰레드별로 다른 SecurityContextHolder 인스턴스를 가지고
    	//있어서 사용자 별로 각기 다른 인증 객체를 가질 수 있다. 
    	String token = jwtGenerator.generateToken(authentication);
        
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(user -> user.getAuthority())
                .collect(Collectors.toList());
        
        return new ResponseEntity<>(new AuthDto(
        			token,
	        		userDetails.getId(),
	        		userDetails.getUsername(),
	        		roles
        		), HttpStatus.OK);
    }
}
