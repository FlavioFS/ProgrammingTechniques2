package br.ufc.dc.tp2.flaviofs.bank.persistence;

import br.ufc.dc.tp2.flaviofs.bank.account.AbstractAccount;
import br.ufc.dc.tp2.flaviofs.bank.persistence.exception.AccountCreationException;
import br.ufc.dc.tp2.flaviofs.bank.persistence.exception.AccountDeletionException;
import br.ufc.dc.tp2.flaviofs.bank.persistence.exception.FlushException;
import br.ufc.dc.tp2.flaviofs.bank.persistence.exception.AccountNotFoundException;

public interface IAccountRepository {

	public void create(AbstractAccount account) throws AccountCreationException;

	public void delete(String number) throws AccountDeletionException;

	public AbstractAccount retrieve(String number) throws AccountNotFoundException;

	public AbstractAccount[] list();

	public int mumberOfAccounts();

	public void flush() throws FlushException;
}
