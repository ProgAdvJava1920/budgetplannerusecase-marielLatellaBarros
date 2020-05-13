package Repositories;

import Entities.Account;

public interface AccountDAO {
    Account saveAccount(Account account);

    Account getByNameOrIban(Account account);

    Account getByIban(String iban);

    boolean updateAccount(Account account);
}
