package models;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.avaje.ebean.Page;

import play.data.validation.Constraints;
import play.db.ebean.Model;

@Entity
public class Account extends Model {

	@Id
	public Long id;

	@Constraints.Required
	public String owner;
	
	@Constraints.Required
	public String iban;
	
	
    /**
     * Generic query helper for entity account with id Long
     */
    public static Model.Finder<Long,Account> find = new Model.Finder<Long,Account>(Long.class, Account.class);

	/**
	 * Return a page of accounts
	 *
	 * @param page Page to display
	 * @param pageSize Number of accounts per page
	 * @param sortBy Account property used for sorting
	 * @param order Sort order (either or asc or desc)
	 * @param filter Filter applied on the name column
	 */
	public static Page<Account> page(int page, int pageSize, String sortBy, String order, String filter) {
		return 
				find.where()
				.ilike("owner", "%" + filter + "%")
				.orderBy(sortBy + " " + order)
				.findPagingList(pageSize)
				.getPage(page);
	}
    
    
    /**
     * Usef for dropdown when creating transactions.
     * @return
     */
    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Account a: Account.find.orderBy("owner").findList()) {
            options.put(a.id.toString(), a.owner);
        }
        return options;
    }
}
