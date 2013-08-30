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
    	return TODO;
    }
    
    
    /**
     * GET     /transactions/new              	controllers.Applications.create()
     * @return
     */
    public static Result create(){
    	return TODO;
    }
    
    
    /**
     * POST    /transactions                  	controllers.Applications.save()
     * @return
     */
    public static Result save(){
    	return TODO;
    }
    
    /**
     * GET     /transactions/edit              controllers.Applications.edit()
     * @return
     */
    public static Result edit(){
    	return TODO;
    }

    /**
     * GET		/transactions/delete			controllers.Applications.delete(id:Long)
     */
    public static Result delete(Long id) {
    	return TODO;
    }
  
}
