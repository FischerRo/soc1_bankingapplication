package models;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;

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
     * WHAT'S the purpose here?
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
