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
package com.wishbox.db.wishbox_db.service.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import com.wishbox.db.wishbox_db.entity.Relation;
import com.wishbox.db.wishbox_db.service.RelationService;

//IMPORT RELATIONS
import com.wishbox.db.wishbox_db.entity.User;

@Service
public class RelationBaseService {

	private static NamedParameterJdbcTemplate jdbcTemplate;
	
		@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	


    //CRUD METHODS
    
    //CRUD - CREATE
    	
	public Relation insert(Relation obj) {
		Long id = jdbcTemplate.queryForObject("select max(_id) from `relation`", new MapSqlParameterSource(), Long.class);
		obj.set_id(id == null ? 1 : id + 1);
		String sql = "INSERT INTO `relation` (`_id`, `status`) VALUES (:id,:status)";
			SqlParameterSource parameters = new MapSqlParameterSource()
		    .addValue("id", obj.get_id())
			.addValue("status", obj.getStatus());
		
		jdbcTemplate.update(sql, parameters);
		return obj;
	}
	
    	
    //CRUD - REMOVE
    
	public void delete(Long id) {
		String sql = "DELETE FROM `Relation` WHERE `_id`=:id";
		SqlParameterSource parameters = new MapSqlParameterSource()
			.addValue("id", id);
		
		String sql_user = "DELETE FROM `relation_user` WHERE `id_Relation`= :id";
		jdbcTemplate.update(sql_user, parameters);
		jdbcTemplate.update(sql, parameters);
	}

    	
    //CRUD - FIND BY User
    	
	public List<Relation> findByuser(Long iduser) {
		
        String sql = "select * from `Relation` WHERE `_id` IN (SELECT `id_Relation` FROM `Relation_user` WHERE `id_User` = :iduser)";
		
	    SqlParameterSource parameters = new MapSqlParameterSource()
		.addValue("iduser", iduser);
	    
	    return jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(Relation.class));
	}
    	
    //CRUD - GET ONE
    	
	public Relation get(Long id) {
	    
		String sql = "select * from `Relation` where `_id` = :id";
		
	    SqlParameterSource parameters = new MapSqlParameterSource()
			.addValue("id", id);
	    
	    return (Relation) jdbcTemplate.queryForObject(sql, parameters, new BeanPropertyRowMapper(Relation.class));
	}


    	
        	
    //CRUD - GET LIST
    	
	public List<Relation> getList() {
	    
		String sql = "select * from `Relation`";
		
	    SqlParameterSource parameters = new MapSqlParameterSource();
	    return jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(Relation.class));
	    
	    
	}

    	
    //CRUD - EDIT
    	
	public Relation update(Relation obj, Long id) {

		String sql = "UPDATE `Relation` SET `status` = :status  WHERE `_id`=:id";
		SqlParameterSource parameters = new MapSqlParameterSource()
			.addValue("id", id)
			.addValue("status", obj.getStatus());
		jdbcTemplate.update(sql, parameters);
	    return obj;
	}
	
    	
    
    
    
    
    // External relation m:m user
    public static class Relation_userService {

    	public static ArrayList<Long> findBy_Relation(Long id_Relation) {
    		String sql = "select `id_User` from Relation_user WHERE `id_Relation`=:id_Relation";
    		SqlParameterSource parameters = new MapSqlParameterSource()
    			.addValue("id_Relation", id_Relation);
    	    
    	    List<Long> listId = jdbcTemplate.queryForList(sql, parameters, Long.class);
    		return (ArrayList<Long>) listId;
    	}
    	

    	public static void updateRelation(Long id_Relation, ArrayList<Long> user) {

    		// Delete not in array
    		String in = " and `id_User` NOT IN (:user)";
    		String sql = "DELETE FROM Relation_user WHERE `id_Relation`=:id_Relation ";
    		
    		if (user != null && user.size() > 0)
    			sql = sql + in;
    			
    		SqlParameterSource parameters = new MapSqlParameterSource()
    			.addValue("id_Relation", id_Relation)
    			.addValue("user", user);
    		
    		jdbcTemplate.update(sql, parameters);
    		
    		// Get actual    		
    	    List<Long> actual = RelationService.Relation_userService.findBy_Relation(id_Relation);
    	    
    		// Insert new
    		for (Long id_User : user) {
    			if (actual.indexOf(id_User) == -1){
    				RelationService.Relation_userService.insert(id_Relation, id_User);
    			}
    		}
    		
    	}
    	

    	public static void insert(Long id_Relation, Long id_User) {
    		Relation.Relation_user obj = new Relation.Relation_user();
			Long id = jdbcTemplate.queryForObject("select max(_id) FROM Relation_user", new MapSqlParameterSource(), Long.class);
			obj.set_id(id == null ? 1 : id + 1);
			
			String sql = "INSERT INTO Relation_user (`_id`, `id_Relation`, `id_User` )	VALUES (:id, :id_Relation, :id_User)";

			MapSqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("id", obj.get_id())
				.addValue("id_Relation", id_Relation)
				.addValue("id_User", id_User);

			jdbcTemplate.update(sql, parameters);
    	}

    }
	

    
    /*
     * CUSTOM SERVICES
     * 
     *	These services will be overwritten and implemented in RelationService.java
     */
    

}
