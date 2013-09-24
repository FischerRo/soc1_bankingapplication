package controllers;

import static play.data.Form.form;

import models.Customer;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;
import views.html.customerFormCreate;
import views.html.customerFormEdit;

import javax.persistence.PersistenceException;

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
        Form<Customer> customerForm = form(Customer.class);
        return ok(
                customerFormCreate.render(customerForm)
        );
    }

    public static Result save(){

        Form<Customer> customerForm = form(Customer.class).bindFromRequest();
        if(customerForm.hasErrors()){
            return badRequest(customerFormCreate.render(customerForm));
        }
        customerForm.get().save();
        flash("success", "Customer" + customerForm.get().lastName + " has been created");
        return Application.GO_CUSTOMERS;


    }

    public static Result edit(Long id){
        Form<Customer> customerForm = form(Customer.class).fill(
                Customer.find.byId(id));
        return ok(
                customerFormEdit.render(id, customerForm)
        );
    }

    public static Result update(Long id){
        Form<Customer> customerForm = form(Customer.class).bindFromRequest();
        if(customerForm.hasErrors()){
            return badRequest(customerFormEdit.render(id, customerForm));
        }
        customerForm.get().update(id);
        flash("success", "Customer \"" + customerForm.get().lastName + "\" has been changed");
        return Application.GO_CUSTOMERS;
    }

    public static Result delete(Long id){
        try{
            Customer.find.ref(id).delete();
            flash("success", "Customer has been deleted");
        }
        catch (PersistenceException pe){
            flash("error", "Customer \"" + Customer.find.ref(id).lastName + "\" cannot be deleted. Found referencing Accounts.");
        }
        return Application.GO_CUSTOMERS;
    }

}