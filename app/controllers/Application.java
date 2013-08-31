package controllers;

import static play.data.Form.form;
import models.Transaction;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class Application extends Controller {
  
	
	/**
	 * This result directly redirect to application home.
	 */
	public static Result GO_HOME = redirect(
		routes.Application.list(0, "date", "desc", "")
	);

    
    public static Result index() {
        return GO_HOME;
    }
    

    /**
     * Display the paginated list of transactions.
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on transaction VWZ
     */
    public static Result list(int page, String sortBy, String order, String filter) {
    	
        return ok(
                list.render(  //requires import views.html.*;
                    Transaction.page(page, 10, sortBy, order, filter),
                    sortBy, order, filter
                )
            );
    	
    }
    
    /**
     * Display the 'edit form' of a existing Transaction.
     *
     * @param id Id of the computer to edit
     */
    public static Result edit(Long id) {
    	return TODO;
    }
    
    
    /**
     * Handle the 'edit form' submission 
     *
     * @param id Id of the transaction to edit
     */
    public static Result update(Long id) {
        return TODO;
    }
    
    
    /**
     * Display the 'new transaction form'.
     */
    public static Result create() {
        Form<Transaction> transactionForm = form(Transaction.class);
        return ok(
        	createForm.render(transactionForm)
        );
    }
    
    /**
     * Handle the 'new transaction form' submission 
     */
    public static Result save(){
    	Form<Transaction> transactionForm = form(Transaction.class).bindFromRequest();
        if(transactionForm.hasErrors()) {
            return badRequest(createForm.render(transactionForm));
        }
        transactionForm.get().save();
        flash("success", "Transaction from account " + transactionForm.get().accountFrom + " to account " + transactionForm.get().accountTo + " has been created");
        return GO_HOME;

    	
    }
    

    /**
     * Handle computer deletion
     */
    public static Result delete(Long id) {
//        Computer.find.ref(id).delete();
//        flash("success", "Computer has been deleted");
//        return GO_HOME;
    	return TODO;
    }
  
}
