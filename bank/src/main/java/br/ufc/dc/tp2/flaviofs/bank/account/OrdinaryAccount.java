package br.ufc.dc.tp2.flaviofs.bank.account;

import br.ufc.dc.tp2.flaviofs.bank.account.exception.InsufficientFundsException;
import br.ufc.dc.tp2.flaviofs.bank.account.exception.NegativeAmountException;

public class OrdinaryAccount extends AbstractAccount {

	public OrdinaryAccount(String number) {
		super(number);
	}

	public void debit(double amount) throws NegativeAmountException, InsufficientFundsException {
		if (amount > 0) {
			if (this.balance >= amount) {
				this.balance = this.balance - amount;
			} else {
				throw new InsufficientFundsException(number, amount);
			}
		} else {
			throw new NegativeAmountException(amount);
		}
	}
}