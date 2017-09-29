package br.ufc.dc.tp2.flaviofs.bank.persistence.exception;

public class AccountNotFoundException extends PersistenceException {

	private static final long serialVersionUID = 1L;

	public AccountNotFoundException(String message, String number) {
		super(message, number);
	}

}
