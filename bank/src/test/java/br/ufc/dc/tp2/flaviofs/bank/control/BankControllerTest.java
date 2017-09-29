package br.ufc.dc.tp2.flaviofs.bank.control;

import static org.junit.Assert.*;

import org.junit.Test;

import br.ufc.dc.tp2.flaviofs.bank.account.AbstractAccount;
import br.ufc.dc.tp2.flaviofs.bank.account.OrdinaryAccount;
import br.ufc.dc.tp2.flaviofs.bank.control.exception.BankTransactionException;
import br.ufc.dc.tp2.flaviofs.bank.persistence.AccountVector;
import br.ufc.dc.tp2.flaviofs.bank.persistence.IAccountRepository;

public class BankControllerTest {

	@Test
	public void testDoTransfer() {
		IAccountRepository repo = new AccountVector();
		BankController bank = new BankController(repo);
		AbstractAccount fromOrd = new OrdinaryAccount("123A");
		AbstractAccount toSav = new OrdinaryAccount("123C");
		try {
			bank.addAccount(fromOrd);
			bank.addAccount(toSav);
		} catch (BankTransactionException e) {
			fail("Bank failed to add account.");
		}
		
		try {
			bank.doCredit("123A", 70);
			bank.doTransfer("123A", "123C", 45);
		} catch (BankTransactionException e) {
			fail("Bank failed to transfer.");
		}
		
		try {
			assertEquals("Wrong balance (sender).", 25, bank.getBalance("123A"), 0.0);
			assertEquals("Wrong balance (receiver).", 45, bank.getBalance("123C"), 0.0);
		} catch (BankTransactionException e) {
			fail("Bank failed to get balance.");
		}
	}
	
	@Test
	public void testDoCredit() {
		IAccountRepository repo = new AccountVector();
		BankController bank = new BankController(repo);
		AbstractAccount ord = new OrdinaryAccount("123A");
		try {
			bank.addAccount(ord);
		} catch (BankTransactionException e) {
			fail("Bank failed to add account.");
		}
		
		try {
			bank.doCredit("123A", 70);
		} catch (BankTransactionException e) {
			fail("Bank failed to credit.");
		}
		
		try {
			assertEquals("Wrong balance.", 70, bank.getBalance("123A"), 0.0);
		} catch (BankTransactionException e) {
			fail("Bank failed to get balance.");
		}
	}
	
	@Test
	public void testDoDebit() {
		IAccountRepository repo = new AccountVector();
		BankController bank = new BankController(repo);
		AbstractAccount ord = new OrdinaryAccount("123A");
		try {
			bank.addAccount(ord);
		} catch (BankTransactionException e) {
			fail("Bank failed to add account.");
		}
		
		try {
			bank.doCredit("123A", 70);
			bank.doDebit("123A", 31);
		} catch (BankTransactionException e) {
			fail("Bank failed to debit.");
		}
		
		try {
			assertEquals("Wrong balance.", 39, bank.getBalance("123A"), 0.0);
		} catch (BankTransactionException e) {
			fail("Bank failed to get balance.");
		}
	}

}
