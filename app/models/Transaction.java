package models;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import com.avaje.ebean.Page;

/**
 * Computer entity managed by Ebean
 */
@Entity 
public class Transaction extends Model {

	@Id
	public Long id;

	@ManyToOne
	public Account accountFrom;
	
	@ManyToOne
	public Account accountTo;
	
	public String reference;

	@Formats.DateTime(pattern="yyyy-MM-dd")
	public Date date;
	
	@Constraints.Required
	public BigInteger value;	//TODO:Format to 2 Decimals

	@Constraints.Required
	public String currency; //TODO:turn to Enum

	/**
	 * Generic query helper for entity Computer with id Long
	 */
	public static Finder<Long,Transaction> find = new Finder<Long,Transaction>(Long.class, Transaction.class); 

	/**
	 * Return a page of computer
	 *
	 * @param page Page to display
	 * @param pageSize Number of computers per page
	 * @param sortBy Computer property used for sorting
	 * @param order Sort order (either or asc or desc)
	 * @param filter Filter applied on the name column
	 */
	public static Page<Transaction> page(int page, int pageSize, String sortBy, String order, String filter) {
		return 
				find.where()
				.ilike("reference", "%" + filter + "%")
				.orderBy(sortBy + " " + order)
				.fetch("accountTo")	//TODO: is this gonna work? -> just copied..
				.findPagingList(pageSize)
				.getPage(page);
	}

}