package controllers;

import static play.data.Form.form;

import javax.persistence.PersistenceException;

import models.Account;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class Accounts extends Controller {
	
	/**
	 * GET		/accounts					controllers.Accounts.list(p:Int ?= 0, s ?= "owner", o ?= "asc", f ?= "")
	 * @param page
	 * @param sortBy
	 * @param order
	 * @param filter
	 * @return
	 */
	public static Result list(int page, String sortBy, String order, String filter){
	
        return ok(
                accountFormList.render(  //requires import views.html.*;
                    Account.page(page, 10, sortBy, order, filter),
                    sortBy, order, filter
                )
            );
	}

	/**
	 * GET     /accounts/new              	controllers.Accounts.create()
	 * Display the AccountCreationForm
	 * @return
	 */
	public static Result create(){
		Form<Account> accountForm = form(Account.class);
		return ok(
				accountFormCreate.render(accountForm)
		);
	}
	
	/**
	 * POST    /accounts                  	controllers.Accounts.save()
	 * @return
	 */
	public static Result save(){
		Form<Account> accountForm = form(Account.class).bindFromRequest();
		if(accountForm.hasErrors()){
			return badRequest(accountFormCreate.render(accountForm));
		}
		accountForm.get().save();
		flash("success", "Account " + accountForm.get().owner + " has been created");
		return Application.GO_ACCOUNTS;
	}

//	# Edit accounts
	/**
	 * GET     /accounts/:id              	controllers.Accounts.edit(id:Long)
	 * @param id
	 * @return
	 */
	public static Result edit(Long id){
		Form<Account> accountForm = form(Account.class).fill(
				Account.find.byId(id));
		return ok(
				accountFormEdit.render(id, accountForm)
		);
	}
	
	/**
	 * POST    /accounts/:id              	controllers.Accounts.update(id:Long)
	 * @param id
	 * @return
	 */
	public static Result update(Long id){
		Form<Account> accountForm = form(Account.class).bindFromRequest();
		if(accountForm.hasErrors()){
			return badRequest(accountFormEdit.render(id, accountForm));
		}
		accountForm.get().update(id);
		flash("success", "Account " + accountForm.get().owner + " has been changed");
		return Application.GO_ACCOUNTS;
	}

	/**
	 * POST    /accounts/:id/delete       	controllers.Accounts.delete(id:Long)
	 * @param id
	 * @return
	 */
	public static Result delete(Long id){

		try{
			Account.find.ref(id).delete();
			flash("success", "Account has been deleted");
		} 
		catch (PersistenceException pe){
			flash("error", "Account " + Account.find.ref(id).owner + " cannot be deleted. Found referencing transactions.");
		}
		return Application.GO_ACCOUNTS;
	}


}
