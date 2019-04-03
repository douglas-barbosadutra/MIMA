package com.UserMicroservice.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UserMicroservice.converter.UserConverter;
import com.UserMicroservice.dao.UserDAO;
import com.UserMicroservice.dto.LoginDTO;
import com.UserMicroservice.dto.UserDTO;
import com.UserMicroservice.utils.JwtUtils;

@Service
public class LoginService {
	
	@Autowired
	private UserDAO userDAO;
	
	public LoginService() {
		
	}
	
	public UserDTO login(LoginDTO loginDTO) {
		
		return UserConverter.toDTO(userDAO.findUserByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword()));
	}
	
	public String createJwt(String subject, String name, String permission, Date dateNow) throws UnsupportedEncodingException {
		
		 Date expDate = dateNow;
        expDate.setTime(dateNow.getTime() + (7200*1000));
       
        String token = JwtUtils.generateJwt(subject, expDate, name, permission);
        return token;
	}
	
	public Map<String,Object> verifyJwtAndGetData(HttpServletRequest request) throws Exception{
		
		String jwt = JwtUtils.getJwtFromHttpRequest(request);
        if(jwt == null){
            throw new Exception("Authentication token not found in the request");
        }
        Map<String, Object> userData = JwtUtils.jwt2Map(jwt);
        return userData;
	}
	
	
}
