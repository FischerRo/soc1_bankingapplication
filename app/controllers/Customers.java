package controllers;

import static play.data.Form.form;

import models.Customer;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

/**
 * Created with IntelliJ IDEA.
 * User: robin
 * Date: 24.09.13
 * Time: 11:20
 * To change this template use File | Settings | File Templates.
 */
public class Customers extends Controller {


    public static Result list(int page, String sortBy, String order, String filter){

        return ok(
                customerFormList.render(  //requires import views.html.*;
                        Customer.page(page, 10, sortBy, order, filter),
                        sortBy, order, filter
                )
        );
    }



    public static Result create(){
        return TODO;
    }

    public static Result save(){
        return TODO;
    }

    public static Result edit(Long id){
        return TODO;
    }

    public static Result update(Long id){
        return TODO;
    }

    public static Result delete(Long id){
        return TODO;
    }

}