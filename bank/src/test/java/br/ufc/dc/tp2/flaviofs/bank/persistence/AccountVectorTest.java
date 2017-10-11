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

public class AccountVectorTest {

	AccountVector av;
	OrdinaryAccount ordinary;
	SavingsAccount savings;
	SpecialAccount special;
	TaxAccount tax;
	
	@Before
	public void setUp() throws Exception {
		av = new AccountVector();
		ordinary = new OrdinaryAccount("ord1");
		savings = new SavingsAccount("sav1");
		special = new SpecialAccount("spc1");
		tax = new TaxAccount("tax1");
		
		try {
			av.create(ordinary);
		} catch (AccountCreationException e) {
			fail("Setup error (Ordinary creation).");
		};
		
		try {
			av.create(savings);
		} catch (AccountCreationException e) {
			fail("Setup error (Savings creation).");
		};
		
		try {
			av.create(special);
		} catch (AccountCreationException e) {
			fail("Setup error (Special creation).");
		};
		
		try {
			av.create(tax);
		} catch (AccountCreationException e) {
			fail("Setup error (Tax creation).");
		};
	}

	@Test
	public void testCreate() {
		ordinary = new OrdinaryAccount("ord2");
		savings = new SavingsAccount("sav2");
		special = new SpecialAccount("spc2");
		tax = new TaxAccount("tax2");
		
		try {
			av.create(ordinary);
		} catch (AccountCreationException e) {
			e.printStackTrace();
			fail("Creation exception (ordinary)");
		};
		
		try {
			av.create(savings);
		} catch (AccountCreationException e) {
			e.printStackTrace();
			fail("Creation exception (savings)");
		};
		
		try {
			av.create(special);
		} catch (AccountCreationException e) {
			e.printStackTrace();
			fail("Creation exception (special)");
		};
		
		try {
			av.create(tax);
		} catch (AccountCreationException e) {
			e.printStackTrace();
			fail("Creation exception (tax)");
		};
	}

	@Test
	public void testRetrieve() {
		try {
			av.retrieve("ord1");
		} catch (AccountNotFoundException e) {
			e.printStackTrace();
			fail("Retrieve exception (ordinary)");
		}
		
		try {
			av.retrieve("sav1");
		} catch (AccountNotFoundException e) {
			e.printStackTrace();
			fail("Retrieve exception (savings)");
		}
		
		try {
			av.retrieve("spc1");
		} catch (AccountNotFoundException e) {
			e.printStackTrace();
			fail("Retrieve exception (special)");
		}
		
		try {
			av.retrieve("tax1");
		} catch (AccountNotFoundException e) {
			e.printStackTrace();
			fail("Retrieve exception (tax)");
		}
	}

	@Test
	public void testMumberOfAccounts() {
		Assert.assertEquals("Wrong number of accounts.", 4, av.mumberOfAccounts());
	}

	@Test
	public void testList() {
		AbstractAccount[] accs = av.list();
		Assert.assertEquals("List wrong size.", av.mumberOfAccounts(), accs.length);
		Assert.assertEquals("List wrong acc (ordinary).", (OrdinaryAccount)accs[0], ordinary);
		Assert.assertEquals("List wrong acc (savings).", (SavingsAccount)accs[1], savings);
		Assert.assertEquals("List wrong acc (special).", (SpecialAccount)accs[2], special);
		Assert.assertEquals("List wrong acc (tax).", (TaxAccount)accs[3], tax);
	}
	
	@Test
	public void testDelete() {
		try {
			av.delete("ord1");
		} catch (AccountDeletionException e) {
			e.printStackTrace();
			fail("Delete exception (ordinary)");
		}
		
		try {
			av.delete("sav1");
		} catch (AccountDeletionException e) {
			e.printStackTrace();
			fail("Delete exception (savings)");
		}
		
		try {
			av.delete("spc1");
		} catch (AccountDeletionException e) {
			e.printStackTrace();
			fail("Delete exception (special)");
		}
		
		try {
			av.delete("tax1");
		} catch (AccountDeletionException e) {
			e.printStackTrace();
			fail("Delete exception (tax)");
		}
	}
}
