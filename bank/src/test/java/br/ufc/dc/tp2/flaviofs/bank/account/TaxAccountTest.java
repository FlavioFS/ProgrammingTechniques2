package br.ufc.dc.tp2.flaviofs.bank.account;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.ufc.dc.tp2.flaviofs.bank.account.exception.InsufficientFundsException;
import br.ufc.dc.tp2.flaviofs.bank.account.exception.NegativeAmountException;

public class TaxAccountTest {

	private TaxAccount acc;
	
	@Before
	public void setUp() throws Exception {
		acc = new TaxAccount("123B");
	}
	
	@Test
	public void testCredit50Debit30() {
		try {
			acc.credit(50);
			assertEquals("Wrong balance (credit).", 50, acc.getBalance(), 0.0);
		} catch (NegativeAmountException err) {
			fail("Negative balance (credit).");
			err.printStackTrace();
		}
		
		try {
			acc.debit(30);
			assertEquals("Wrong balance (debit).", 19.97, acc.getBalance(), 0.0);
		} catch (NegativeAmountException err) {
			fail("Negative balance (debit).");
			err.printStackTrace();
		} catch (InsufficientFundsException err) {
			fail("Insufficient funds (debit).");
			err.printStackTrace();
		}
	}
	
	
	@Test
	public void testDebit() {
		try {
			acc.credit(2000);
			assertEquals("Wrong balance (credit).", 2000, acc.getBalance(), 0.0);
			acc.debit(1000);
			assertEquals("Wrong balance (debit).", 999, acc.getBalance(), 0.0);
		} catch (NegativeAmountException e) {
			fail("Negative balance.");
		} catch (InsufficientFundsException e) {
			fail("Insufficient funds.");
		}
	}

	@Test
	public void testCredit() {
		try {
			acc.credit(47);
			assertEquals("Wrong balance.", 47, acc.getBalance(), 0.0);
		} catch (NegativeAmountException e) {
			fail("Negative balance.");
		}
	}

}
