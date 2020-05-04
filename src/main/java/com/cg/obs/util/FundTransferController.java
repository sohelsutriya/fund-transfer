/**
 * 
 */
package com.cg.obs.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.obs.model.Account;
import com.cg.obs.model.Transaction;
import com.cg.obs.model.TransactionType;
import com.cg.obs.service.FundTransferService;

/**
 * @author sohel
 *
 */
@RestController
public class FundTransferController {
	@Autowired
	FundTransferService service;

	@PostMapping(path = "/fundtransfer/from/{senderAccountNo}/to/{recieverAccountNo}/{amount}")
	public String fundTransfer(@PathVariable String senderAccountNo, @PathVariable String recieverAccountNo,
			@PathVariable double amount) {
		System.out.println("this will transfer funds from one account to other and update transactions also");
		Account senderAccount = service.getAccountByAccountNo(senderAccountNo);
		senderAccount.setBalance(senderAccount.getBalance() - amount);
		service.updateAccount(senderAccount);
		service.postTransaction(new Transaction(null, TransactionType.Debit, new Date(), amount, senderAccountNo));
		Account recieverAccount = service.getAccountByAccountNo(recieverAccountNo);
		recieverAccount.setBalance(recieverAccount.getBalance() + amount);
		service.updateAccount(recieverAccount);
		service.postTransaction(new Transaction(null, TransactionType.Credit, new Date(), amount, recieverAccountNo));
		return "fund transferred";
	}
}
