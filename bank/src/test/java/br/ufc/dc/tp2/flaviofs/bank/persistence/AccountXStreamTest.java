package br.ufc.dc.tp2.flaviofs.bank.persistence;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ufc.dc.tp2.flaviofs.bank.account.AbstractAccount;
import br.ufc.dc.tp2.flaviofs.bank.account.OrdinaryAccount;
import br.ufc.dc.tp2.flaviofs.bank.account.SavingsAccount;
import br.ufc.dc.tp2.flaviofs.bank.account.SpecialAccount;
import br.ufc.dc.tp2.flaviofs.bank.account.TaxAccount;
import br.ufc.dc.tp2.flaviofs.bank.persistence.exception.AccountCreationException;
import br.ufc.dc.tp2.flaviofs.bank.persistence.exception.AccountDeletionException;
import br.ufc.dc.tp2.flaviofs.bank.persistence.exception.AccountNotFoundException;

public class AccountXStreamTest {

	AccountXStream ax;
	OrdinaryAccount ordinary;
	SavingsAccount savings;
	SpecialAccount special;
	TaxAccount tax;
	
	@Before
	public void setUpBeforeClass() throws Exception {
		ax = new AccountXStream();
		ordinary = new OrdinaryAccount("ord1");
		savings = new SavingsAccount("sav1");
		special = new SpecialAccount("spc1");
		tax = new TaxAccount("tax1");
		
		// Delete persistent accounts ------------------------
		try {
			ax.delete("ord1");
		} catch (AccountDeletionException e) { }
		
		try {
			ax.delete("sav1");
		} catch (AccountDeletionException e) { }
		
		try {
			ax.delete("spc1");
		} catch (AccountDeletionException e) { }
		
		try {
			ax.delete("tax1");
		} catch (AccountDeletionException e) { }
		
		// Create test cases ----------------------------------
		try {
			ax.create(ordinary);
		} catch (AccountCreationException e) {
			fail("Setup failed (Ordinary creation)");
		}
		
		try {
			ax.create(savings);
		} catch (AccountCreationException e) {
			fail("Setup failed (Savings creation)");
		}
		
		try {
			ax.create(special);
		} catch (AccountCreationException e) {
			fail("Setup failed (Special creation)");
		}
		
		try {
			ax.create(tax);
		} catch (AccountCreationException e) {
			fail("Setup failed (Tax creation)");
		}
	}

	@Test
	public void testDelete() {
		try {
			ax.delete("tax1");
		} catch (AccountDeletionException e) {
			e.printStackTrace();
			fail("Deletion Exception");
		}
	}

	@Test
	public void testCreate() {
		ax = new AccountXStream();
		ordinary = new OrdinaryAccount("ord2");
		savings = new SavingsAccount("sav2");
		special = new SpecialAccount("spc2");
		tax = new TaxAccount("tax2");
		
		try {
			ax.create(ordinary);
		} catch (AccountCreationException e) {
			e.printStackTrace();
			fail("Creation Exception (Ordinary).");
		}
		
		try {
			ax.create(savings);
		} catch (AccountCreationException e) {
			e.printStackTrace();
			fail("Creation Exception (Savings).");
		}
		
		try {
			ax.create(special);
		} catch (AccountCreationException e) {
			e.printStackTrace();
			fail("Creation Exception (Special).");
		}
		
		try {
			ax.create(tax);
		} catch (AccountCreationException e) {
			e.printStackTrace();
			fail("Creation Exception (Tax).");
		}
		
		// Cleaning -------------------------------------
		
		try {
			ax.delete("ord2");
			ax.delete("sav2");
			ax.delete("spc2");
			ax.delete("tax2");
		} catch (AccountDeletionException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testList() {
		AbstractAccount[] accs = ax.list();
		
		try {
			Assert.assertEquals("Wrong list length.", 4, accs.length);
			Assert.assertEquals("Wrong type (OrdinaryAccount).", accs[0] instanceof OrdinaryAccount, true);
			Assert.assertEquals("Wrong type (SavingsAccount).", accs[1] instanceof SavingsAccount, true);
			Assert.assertEquals("Wrong type (SpecialAccount).", accs[2] instanceof SpecialAccount, true);
			Assert.assertEquals("Wrong type (TaxAccount).", accs[3] instanceof TaxAccount, true);
			Assert.assertEquals("Wrong account (OrdinaryAccount).", accs[0].getNumber(), "ord1");
			Assert.assertEquals("Wrong account (SavingsAccount).", accs[1].getNumber(), "sav1");
			Assert.assertEquals("Wrong account (SpecialAccount).", accs[2].getNumber(), "spc1");
			Assert.assertEquals("Wrong account (TaxAccount).", accs[3].getNumber(), "tax1");
		} catch (Exception e) {
			fail("List exception.");
		}
	}

	@Test
	public void testMumberOfAccounts() {
		Assert.assertEquals("Wrong list length.", 4, ax.mumberOfAccounts());
	}

	@Test
	public void testRetrieve() {
		try {
			OrdinaryAccount ord = (OrdinaryAccount) ax.retrieve("ord1");
			Assert.assertEquals("Wrong account (OrdinaryAccount).", ord.getNumber(), "ord1");
		} catch (AccountNotFoundException e) {
			e.printStackTrace();
			fail("Account not found (Ordinary).");
		}
		
		try {
			SavingsAccount sav = (SavingsAccount) ax.retrieve("sav1");
			Assert.assertEquals("Wrong account (SavingsAccount).", sav.getNumber(), "sav1");
		} catch (AccountNotFoundException e) {
			e.printStackTrace();
			fail("Account not found (Savings).");
		}
		
		try {
			SpecialAccount spc = (SpecialAccount) ax.retrieve("spc1");
			Assert.assertEquals("Wrong account (SpecialAccount).", spc.getNumber(), "spc1");
		} catch (AccountNotFoundException e) {
			e.printStackTrace();
			fail("Account not found (Special).");
		}
		
		try {
			TaxAccount tax = (TaxAccount) ax.retrieve("tax1");
			Assert.assertEquals("Wrong account (TaxAccount).", tax.getNumber(), "tax1");
		} catch (AccountNotFoundException e) {
			e.printStackTrace();
			fail("Account not found (Tax).");
		}
	}
}
