package controllers;

import static play.data.Form.form;
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
		return TODO;
	}

	/**
	 * GET     /accounts/new              	controllers.Accounts.create()
	 * Display the AccountCreationForm
	 * @return
	 */
	public static Result create(){
		Form<Account> accountForm = form(Account.class);
		return ok(
				createAccountForm.render(accountForm)
		);
	}
	
	/**
	 * POST    /accounts                  	controllers.Accounts.save()
	 * @return
	 */
	public static Result save(){
		Form<Account> accountForm = form(Account.class).bindFromRequest();
		if(accountForm.hasErrors()){
			return badRequest(createAccountForm.render(accountForm));
		}
		accountForm.get().save();
		flash("success", "Account " + accountForm.get().owner + " has been created");
		return Application.GO_HOME;
	}

//	# Edit accounts
	/**
	 * GET     /accounts/:id              	controllers.Accounts.edit(id:Long)
	 * @param id
	 * @return
	 */
	public static Result edit(Long id){
		return TODO;
	}
	
	/**
	 * POST    /accounts/:id              	controllers.Accounts.update(id:Long)
	 * @param id
	 * @return
	 */
	public static Result update(Long id){
		return TODO;
	}

//	# Delete a transaction
	/**
	 * POST    /accounts/:id/delete       	controllers.Accounts.delete(id:Long)
	 * @param id
	 * @return
	 */
	public static Result delete(Long id){
		return TODO;
	}


}
