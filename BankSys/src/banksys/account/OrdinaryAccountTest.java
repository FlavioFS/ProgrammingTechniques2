package banksys.account;

import static org.junit.Assert.*;

import org.junit.Test;

import banksys.account.exception.InsufficientFundsException;
import banksys.account.exception.NegativeAmountException;

public class OrdinaryAccountTest {

	@Test
	public void testDebit() {
		AbstractAccount acc = new OrdinaryAccount("id");
		try {
			acc.credit(50);
			assertEquals("Wrong balance (credit).", 50, acc.getBalance(), 0.0);
			acc.debit(30);
			assertEquals("Wrong balance (debit).", 20, acc.getBalance(), 0.0);
		} catch (NegativeAmountException e) {
			fail("Negative balance.");
		} catch (InsufficientFundsException e) {
			fail("Insufficient funds.");
		}
	}

	@Test
	public void testCredit() {
		AbstractAccount acc = new OrdinaryAccount("id");
		try {
			acc.credit(50);
			assertEquals("Wrong balance.", 50, acc.getBalance(), 0.0);
		} catch (NegativeAmountException e) {
			fail("Negative balance.");
		}
	}

}
