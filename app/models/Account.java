package models;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.persistence.*;

import play.data.format.Formatters.SimpleFormatter;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import com.avaje.ebean.Page;

@Entity
public class Account extends Model {

    @Id
    public Long id;

    @ManyToOne
    @Constraints.Required
    public Customer owner;

    @Constraints.Required
    public String iban;

    @Constraints.Required
    @Enumerated(EnumType.STRING)
    public Type typeOf;

    public enum Type{
        Checking, Savings, Loan
    }


    /**
     * Generic query helper for entity account with id Long
     */
    public static Model.Finder<Long, Account> find = new Model.Finder<Long, Account>(Long.class, Account.class);

    /**
     * Return a page of accounts
     *
     * @param page     Page to display
     * @param pageSize Number of accounts per page
     * @param sortBy   Account property used for sorting
     * @param order    Sort order (either or asc or desc)
     * @param filter   Filter applied on the name column
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
     * Used for dropdown when creating transactions.
     *
     * @return
     */
    public static Map<String, String> options() {
        LinkedHashMap<String, String> options = new LinkedHashMap<String, String>();
        for (Account a : Account.find.orderBy("owner").findList()) {
            options.put(a.id.toString(), a.owner.toString() + " (" + a.typeOf + ")");
        }
        return options;
    }


    static {
        play.data.format.Formatters.register(Account.class, new AccountFormatter());
    }

    public static class AccountFormatter extends SimpleFormatter<Account> {

        @Override
        public Account parse(String text, Locale locale) {
            if (text == null || text.trim().length() == 0)
                return null;
            return Account.find.byId(Long.parseLong(text.trim()));
        }

        @Override
        public String print(Account value, Locale locale) {
            return (value == null || value.id == null ? "" : value.id.toString());
        }
    }
}
