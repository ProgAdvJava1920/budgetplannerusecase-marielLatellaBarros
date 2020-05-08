package Repositories;

import Entities.Account;

public interface AccountDAO {
    Account saveAccount(Account account);

    Account getByName(String name);

    Account getByIban(String iban);
}
