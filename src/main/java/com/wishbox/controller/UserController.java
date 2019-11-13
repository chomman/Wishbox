/* 
* Generated by
* 
*      _____ _          __  __      _     _
*     / ____| |        / _|/ _|    | |   | |
*    | (___ | | ____ _| |_| |_ ___ | | __| | ___ _ __
*     \___ \| |/ / _` |  _|  _/ _ \| |/ _` |/ _ \ '__|
*     ____) |   < (_| | | | || (_) | | (_| |  __/ |
*    |_____/|_|\_\__,_|_| |_| \___/|_|\__,_|\___|_|
*
* The code generator that works in many programming languages
*
*			https://www.skaffolder.com
*
*
* You can generate the code from the command-line
*       https://npmjs.com/package/skaffolder-cli
*
*       npm install -g skaffodler-cli
*
*   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
*
* To remove this comment please upgrade your plan here: 
*      https://app.skaffolder.com/#!/upgrade
*
* Or get up to 70% discount sharing your unique link:
*       https://app.skaffolder.com/#!/register?friend=5dcb97a2f1ef4518a5382d3c
*
* You will get 10% discount for each one of your friends
* 
*/
package com.wishbox.controller;

import org.springframework.context.annotation.PropertySource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import com.wishbox.db.wishbox_db.entity.User;
import com.wishbox.db.wishbox_db.service.UserService;


@RestController
@PropertySource("classpath:${configfile.path}/Wishbox.properties")
@RequestMapping(value="${endpoint.api}/Users", headers = "Accept=application/json")
public class UserController {

	@Autowired
	private UserService userService;
	
	//CRUD - CREATE
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(method = RequestMethod.POST)
	public User insert(@RequestBody User obj){
		return userService.insert(obj);
	}
	
	//CRUD - REMOVE
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id) {
		userService.delete(id);
	}
	
    //CRUD - GET ONE
    @Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User get(@PathVariable Long id)  {
		return userService.get(id);
	}
	
    //CRUD - GET LIST
    @Secured({"ROLE_ADMIN"})
	@RequestMapping(method = RequestMethod.GET)
	public List<User> getList() {
		return userService.getList();
	}
	
    //CRUD - EDIT
    @Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public User update(@RequestBody User obj, @PathVariable("id") Long id) {
		return userService.update(obj, id);
	}
	
	//CHANGE PASSWORD
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "/{id}/changePassword", method = RequestMethod.POST)
	public Map<String, String> changePassword(@PathVariable("id") Long id,@RequestBody Map<String, String> passwords) throws Exception {
		boolean check = userService.changePassword(id, passwords);
		if(check)
			return new HashMap<String,String>();
		else
			return null;
	}
	/*
	 * CUSTOM SERVICES
	 * 
	 */
}
