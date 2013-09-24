package models;

import com.avaje.ebean.Page;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

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
        male, female
    }


    /**
     * Generic query helper for entity Customer with id Long
     */
    public static Model.Finder<Long, Customer> find = new Model.Finder<Long, Customer>(Long.class, Customer.class);


    public static Page<Customer> page(int page, int pageSize, String sortBy, String order, String filter) {
        return
                find.where()
                        .ilike("lastName", "%" + filter + "%")
                        .orderBy(sortBy + " " + order)
                        .findPagingList(pageSize)
                        .getPage(page);
    }

}
