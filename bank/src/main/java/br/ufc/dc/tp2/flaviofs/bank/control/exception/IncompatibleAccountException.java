package br.ufc.dc.tp2.flaviofs.bank.control.exception;

public class IncompatibleAccountException extends BankTransactionException {

	private static final long serialVersionUID = 1L;

	public IncompatibleAccountException(String number) {
		super("Incompatible account type to the requested operation! [account number = " + number + "]");
	}
}
