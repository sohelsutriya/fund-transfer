/**
 * 
 */
package com.cg.obs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cg.obs.model.Account;
import com.cg.obs.model.Transaction;

/**
 * @author sohel
 *
 */
@Service
public class FundTransferService {
	@Autowired
	RestTemplate restTemplate;

	public Account getAccountByAccountNo(String accountNo) {
		return restTemplate.getForEntity("localhost:5010/accounts/" + accountNo, Account.class).getBody();
	}

	public void postTransaction(Transaction transaction) {
		restTemplate.postForEntity("localhost:5030/transactions/add", transaction, Transaction.class);
	}
	
	public void updateAccount(Account account) {
		restTemplate.put("localhost:5010/accounts/update", account);
	}
}
