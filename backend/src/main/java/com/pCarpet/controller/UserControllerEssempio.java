//package com.MetaBot.server.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.MetaBot.server.dto.UserDTO;
//import com.MetaBot.server.services.UserService;
//
//import java.util.List;
//
//import javax.transaction.Transactional;
//
//@CrossOrigin(value="*")
//@RestController
//@RequestMapping("/User")
//public class UserController {
//
//	private final UserService userService;
//	
//	@Autowired
//	public UserController(UserService userService) {
//		this.userService = userService;
//	}
//	
//	@RequestMapping(value = "/all", method = RequestMethod.GET)
//	public List<UserDTO> all() {
//		return this.userService.getAll();
//	}
//	
//	@RequestMapping(value = "/insert", method = RequestMethod.POST)
//	public UserDTO insert(@RequestBody UserDTO userDTO) {
//		return userService.insert(userDTO);
//	}
//	
//	@Transactional
//	@RequestMapping(value = "/delete", method = RequestMethod.GET)
//	public boolean delete(@RequestParam("id") Integer id) {
//		return this.userService.deleteById(id);
//	}
//	
//	@RequestMapping(value = "/cercaUser", method = RequestMethod.GET)
//	public List<UserDTO> cercaUser(@RequestParam("search") String content) {
//		return this.userService.findUserDTOByUsername(content);
//	}
//	
//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public UserDTO login(@RequestParam("username") String username, @RequestParam("password") String password) {
//		return userService.getByUsernameAndPassword(username, password);
//	}
//}
