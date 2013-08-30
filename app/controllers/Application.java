package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {
  
	
    /**
     * This result directly redirect to application home.
     */
    public static Result GO_HOME = redirect(routes.Application.list(0, "name", "asc", ""));

    
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
//    	
//        return ok(
//                list.render(
//                    Transaction.page(page, 10, sortBy, order, filter),
//                    sortBy, order, filter
//                )
//            );
    	return TODO;
    	
    }
    
    /**
     * Display the 'edit form' of a existing Computer.
     *
     * @param id Id of the computer to edit
     */
    public static Result edit(Long id) {
    	return TODO;
    }
    
    
    /**
     * Handle the 'edit form' submission 
     *
     * @param id Id of the computer to edit
     */
    public static Result update(Long id) {
        return TODO;
    }
    
    
    /**
     * Display the 'new transaction form'.
     */
    public static Result create() {
//        Form<Computer> computerForm = form(Computer.class);
//        return ok(
//            createForm.render(computerForm)
//        );
    	return TODO;
    }
    
    /**
     * Handle the 'new transaction form' submission 
     */
    public static Result save(){
    	return TODO;
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
