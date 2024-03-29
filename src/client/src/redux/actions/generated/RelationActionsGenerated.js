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
/**
 *
 *
  _____                      _              _ _ _     _   _     _        __ _ _
 |  __ \                    | |            | (_) |   | | | |   (_)      / _(_) |
 | |  | | ___    _ __   ___ | |_    ___  __| |_| |_  | |_| |__  _ ___  | |_ _| | ___
 | |  | |/ _ \  | '_ \ / _ \| __|  / _ \/ _` | | __| | __| '_ \| / __| |  _| | |/ _ \
 | |__| | (_) | | | | | (_) | |_  |  __/ (_| | | |_  | |_| | | | \__ \ | | | | |  __/
 |_____/ \___/  |_| |_|\___/ \__|  \___|\__,_|_|\__|  \__|_| |_|_|___/ |_| |_|_|\___|

 * DO NOT EDIT THIS FILE!!
 *
 *  TO CUSTOMIZE FUNCTIONS IN RelationActionsGenerated.js PLEASE EDIT ../RelationActions.js
 *
 *  -- THIS FILE WILL BE OVERWRITTEN ON THE NEXT SKAFFOLDER'S CODE GENERATION --
 *
 */

import * as types from "../../actionTypes";
import RelationApi from "../../../api/RelationApi";

let actionsFunction = {

  //CRUD METHODS

  // Create relation
  createRelation: function(relation) {
    return function(dispatch) {
      return RelationApi
        .createRelation(relation)
        .then(relation => {
          dispatch(actionsFunction.createRelationSuccess(relation));
        })
        .catch(error => {
          throw error;
        });
    };
  },

  createRelationSuccess: function(relation) {
    return { type: types.CREATE_RELATION_SUCCESS, payload: relation };
  },


  // Delete relation
  deleteRelation: function(id) {
    return function(dispatch) {
      return RelationApi
        .deleteRelation(id)
        .then(relation => {
          dispatch(actionsFunction.deleteRelationSuccess(relation));
        })
        .catch(error => {
          throw error;
        });
    };
  },

  deleteRelationSuccess: function(relation) {
    return { type: types.DELETE_RELATION_SUCCESS, payload: relation };
  },


  // Find by user
  findByuser: function(key) {
    return function(dispatch) {
      return RelationApi
        .findByuser(key)
        .then(item => {
          dispatch(actionsFunction.findByuserSuccess(item));
        })
        .catch(error => {
          throw error;
        });
    };
  },

  findByuserSuccess: function(item) {
    return { type: types.FINDBYUSER_RELATION_SUCCESS, payload: item };
  },


  // Get relation
  loadRelation: function(id) {
    return function(dispatch) {
      return RelationApi
        .getOneRelation(id)
        .then(relation => {
          dispatch(actionsFunction.loadRelationSuccess(relation));
        })
        .catch(error => {
          throw error;
        });
    };
  },

  loadRelationSuccess: function(relation) {
    return { type: types.GET_RELATION_SUCCESS, payload: relation };
  },

  // Load  list
  loadRelationList: function() {
    return function(dispatch) {
      return RelationApi
        .getRelationList()
        .then(list => {
          dispatch(actionsFunction.loadRelationListSuccess(list));
        })
        .catch(error => {
          throw error;
        });
    };
  },

  loadRelationListSuccess: function(list) {
    return { type: types.LIST_RELATION_SUCCESS, payload: list };
  },

	
  // Save relation
  saveRelation: function(relation) {
    return function(dispatch) {
      return RelationApi
        .saveRelation(relation)
        .then(relation => {
          dispatch(actionsFunction.saveRelationSuccess(relation));
        })
        .catch(error => {
          throw error;
        });
    };
  },

  saveRelationSuccess: function(relation) {
    return { type: types.UPDATE_RELATION_SUCCESS, payload: relation };
  },


};

export default actionsFunction;
