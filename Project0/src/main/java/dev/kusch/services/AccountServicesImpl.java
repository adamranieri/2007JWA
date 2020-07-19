package dev.kusch.services;

import java.util.Set;

import dev.kusch.daos.AccountDAO;
import dev.kusch.daos.AccountDAOMaria;
import dev.kusch.entities.Account;

public class AccountServicesImpl implements AccountServices{

	private static AccountDAO adao = AccountDAOMaria.getAccountDAOMaria();
	
	@Override
	public Account startAccount(Account account) {
		return adao.createAccount(account);
	}

	@Override
	public Set<Account> getAllAccounts() {
		return adao.getAllAccounts();
	}

	@Override
	public Account getAccount(int aid) {
		return adao.getAccountById(aid);
	}

	@Override
	public Account getAccount(Account account) {
		return this.getAccount(account.getaId());
	}

	@Override
	public Set<Account> getAccountsByCustomer(int cid) {
		return adao.getAccountByCustomerId(cid);
	}

	@Override
	public Account updateAccount(Account account) {
		return adao.updateAccount(account);
	}

	@Override
	public boolean deleteAccount(Account account) {
		return this.deleteAccount(account.getaId());
	}

	@Override
	public boolean deleteAccount(int id) {
		return adao.deleteAccount(id);
	}

	@Override
	public Set<Account> getAccountsLessThan(int bound, int cid) {
		return adao.getAccountWithBalanceBetween(cid, 0, bound);
	}

	@Override
	public Set<Account> getAccountsGreaterThan(int bound, int cid) {
		return adao.getAccountWithBalanceBetween(cid, bound, Double.MAX_VALUE);
	}

	@Override
	public Set<Account> getAccountsBetween(int lowerBound, int upperBound, int cid) {
		return adao.getAccountWithBalanceBetween(cid, lowerBound, upperBound);
	}

}
