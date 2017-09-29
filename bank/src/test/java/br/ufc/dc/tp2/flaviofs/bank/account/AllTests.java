package br.ufc.dc.tp2.flaviofs.bank.account;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ OrdinaryAccountTest.class, SavingsAccountTest.class, SpecialAccountTest.class, TaxAccountTest.class })
public class AllTests {

}
