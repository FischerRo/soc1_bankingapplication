package models;

import com.avaje.ebean.Expr;
import com.avaje.ebean.Page;
import play.data.format.Formatters;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: robin
 * Date: 24.09.13
 * Time: 11:10
 * To change this template use File | Settings | File Templates.
 */

@Entity
public class Customer extends Model {

    @Id
    public Long id;

    @Constraints.Required
    public String lastName;

    @Constraints.Required
    public String firstName;

    @Constraints.Required
    @Enumerated(EnumType.STRING)
    public Sex sex;

    @Constraints.Required
    public String city;

    @Constraints.Required
    public int age;

    public String ageGroup;

    public enum Sex{
        m, w
    }


    /**
     * Generic query helper for entity Customer with id Long
     */
    public static Model.Finder<Long, Customer> find = new Model.Finder<Long, Customer>(Long.class, Customer.class);


    public static Page<Customer> page(int page, int pageSize, String sortBy, String order, String filter) {
        return
                find.where()
                        .or(
                                Expr.ilike("lastName", "%" + filter + "%"),
                                Expr.ilike("firstName", "%" + filter + "%")

                        )
                        .orderBy(sortBy + " " + order)
                        .findPagingList(pageSize)
                        .setFetchAhead(false)
                        .getPage(page);
    }


    /**
     * Used for dropdown when creating Accounts.
     *
     * @return
     */
    public static Map<String, String> options() {
        LinkedHashMap<String, String> options = new LinkedHashMap<String, String>();
        for (Customer c : Customer.find.orderBy("lastName").findList()) {
            options.put(c.id.toString(), c.lastName.toString() + ", " + c.firstName.toString());
        }
        return options;
    }


    @Override
    public String toString(){
        return lastName + ", " + firstName;
    }


    static {
        play.data.format.Formatters.register(Customer.class, new CustomerFormatter());
    }

    public static class CustomerFormatter extends Formatters.SimpleFormatter<Customer> {

        @Override
        public Customer parse(String text, Locale locale) {
            if (text == null || text.trim().length() == 0)
                return null;
            return Customer.find.byId(Long.parseLong(text.trim()));
        }

        @Override
        public String print(Customer value, Locale locale) {
            return (value == null || value.id == null ? "" : value.id.toString());
        }
    }


}
