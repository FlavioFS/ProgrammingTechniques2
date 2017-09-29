package br.ufc.dc.tp2.flaviofs.bank.persistence;

import java.util.Vector;

import br.ufc.dc.tp2.flaviofs.bank.account.AbstractAccount;
import br.ufc.dc.tp2.flaviofs.bank.persistence.exception.AccountCreationException;
import br.ufc.dc.tp2.flaviofs.bank.persistence.exception.AccountDeletionException;
import br.ufc.dc.tp2.flaviofs.bank.persistence.exception.FlushException;
import br.ufc.dc.tp2.flaviofs.bank.persistence.exception.AccountNotFoundException;

public class AccountVector implements IAccountRepository {

	private Vector<AbstractAccount> accounts = null;

	public AccountVector() {
		this.accounts = new Vector<AbstractAccount>();
	}

	public void delete(String number) throws AccountDeletionException {
		AbstractAccount account = this.findAccount(number);
		if (account != null) {
			this.accounts.remove(account);
		} else {
			throw new AccountDeletionException("Account doesn't exist!", number);
		}
	}

	public void create(AbstractAccount account) throws AccountCreationException {
		if (this.findAccount(account.getNumber()) == null) {
			this.accounts.addElement(account);
		} else {
			throw new AccountCreationException("Account alredy exist!", account.getNumber());
		}
	}

	public AbstractAccount retrieve(String number) throws AccountNotFoundException {
		AbstractAccount account = findAccount(number);
		if (account != null) {
			return account;
		} else {
			throw new AccountNotFoundException("Account not found!", number);
		}
	}

	public int mumberOfAccounts() {
		return this.accounts.size();
	}

	public AbstractAccount[] list() {
		AbstractAccount[] list = null;
		if (this.accounts.size() > 0) {
			list = new AbstractAccount[this.accounts.size()];
			for (int i = 0; i < this.accounts.size(); i++) {
				list[i] = (AbstractAccount) this.accounts.elementAt(i);
			}
		}
		return list;
	}

	public void flush() throws FlushException {
	}

	private AbstractAccount findAccount(String number) {
		if (this.accounts.size() > 0) {
			for (int i = 0; i < this.accounts.size(); i++) {
				AbstractAccount account = (AbstractAccount) this.accounts.elementAt(i);
				if (account.getNumber().equals(number)) {
					return account;
				}
			}
		}
		return null;
	}
}