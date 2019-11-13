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
package com.wishbox.controller.base;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.ArrayList;
import org.springframework.security.access.annotation.Secured;
import org.springframework.beans.factory.annotation.Autowired;
import com.wishbox.db.wishbox_db.service.RelationService;
import com.wishbox.db.wishbox_db.entity.Relation;

//IMPORT RELATIONS
import com.wishbox.db.wishbox_db.entity.User;

public class RelationBaseController {
    
    @Autowired
	RelationService relationService;



//CRUD METHODS


    //CRUD - CREATE
    @Secured({ "ROLE_PRIVATE_USER" })
		@RequestMapping(value = "/relation", method = RequestMethod.POST, headers = "Accept=application/json")
	public Relation insert(@RequestBody Relation obj) {
		Relation result = relationService.insert(obj);

	    
		//external relation user
		ArrayList<Long> user = obj.getUser();
		if (user != null) {
			RelationService.Relation_userService.updateRelation(result.get_id(), user);
		}
		
		
		return result;
	}

	
    //CRUD - REMOVE
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/relation/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void delete(@PathVariable("id") Long id) {
		relationService.delete(id);
	}
	

    //CRUD - FIND BY User
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/relation/findByuser/{key}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Relation> findByuser(@PathVariable("key") Long iduser) {
		List<Relation> list = relationService.findByuser(iduser);
		return list;
	}
	
    //CRUD - GET ONE
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/relation/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Relation get(@PathVariable Long id) {
		Relation obj = relationService.get(id);
		
		
		//external relation user
		ArrayList<Long> user = RelationService.Relation_userService.findBy_Relation(id);
		obj.setUser(user);
		
		
		return obj;
	}
	
	
    //CRUD - GET LIST
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/relation", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Relation> getList() {
		return relationService.getList();
	}
	
	

    //CRUD - EDIT
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/relation/{id}", method = RequestMethod.POST, headers = "Accept=application/json")
	public Relation update(@RequestBody Relation obj, @PathVariable("id") Long id) {
		Relation result = relationService.update(obj, id);

	    
		//external relation user
		ArrayList<Long> user = obj.getUser();
		if (user != null) {
			RelationService.Relation_userService.updateRelation(id, user);
		}
		
		
		return result;
	}
	


/*
 * CUSTOM SERVICES
 * 
 *	These services will be overwritten and implemented in  Custom.js
 */


	
}
