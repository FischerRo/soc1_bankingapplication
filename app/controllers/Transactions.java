package controllers;

import models.Account;
import models.Transaction;
import play.libs.Json;
import play.mvc.Result;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;

/**
 * @author jacobeberhardt
 *         Date: 30.10.13
 *         Time: 13:34
 */
public class Transactions {

    public static Result add(Long fromId, Long toId, String currency, String category, String status, String purpose, String value) {
        Transaction inst = new Transaction();
        inst.accountFrom = Account.find.byId(fromId);
        inst.accountTo = Account.find.byId(toId);
        if (inst.accountFrom==null || inst.accountTo==null)
            return badRequest(Json.toJson("An AccountId can't be resolved"));
        inst.category = category;
        inst.currency = Transaction.Currency.valueOf(currency);
        inst.date = new Date();
        inst.purpose = purpose;
        inst.status = Transaction.Status.valueOf(status);
        inst.value = BigDecimal.valueOf(Double.valueOf(value));

        inst.save();
        return ok(Json.toJson(inst));
    }


    public static Result addWithDate(Long fromId, Long toId, String currency, String category, String status, String purpose, String value, String date) {

        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date d= format.parse(date);
            Transaction inst = new Transaction();
            inst.accountFrom = Account.find.byId(fromId);
            inst.accountTo = Account.find.byId(toId);
            if (inst.accountFrom==null || inst.accountTo==null)
                return badRequest(Json.toJson("An AccountId can't be resolved"));
            inst.category = category;
            inst.currency = Transaction.Currency.valueOf(currency);
            inst.date = d;
            inst.purpose = purpose;
            inst.status = Transaction.Status.valueOf(status);
            inst.value = BigDecimal.valueOf(Double.valueOf(value));

            inst.save();
            return ok(Json.toJson(inst));

        } catch (ParseException e) {
            return badRequest("Illegal Date format. Use yyyy-MM-dd");
        }

    }
}
