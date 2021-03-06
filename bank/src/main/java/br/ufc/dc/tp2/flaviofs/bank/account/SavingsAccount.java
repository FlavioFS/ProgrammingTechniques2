package br.ufc.dc.tp2.flaviofs.bank.account;

import br.ufc.dc.tp2.flaviofs.bank.account.exception.NegativeAmountException;

public class SavingsAccount extends OrdinaryAccount {

	public SavingsAccount(String number) {
		super(number);
	}

	public void earnInterest() {
		try {
			this.credit(this.getBalance() * 0.001);
		} catch (NegativeAmountException e) {
		}
	}
}
