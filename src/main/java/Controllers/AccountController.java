package Controllers;

import DataTransferObject.AccountDTO;
import Entities.Account;
import Repositories.AccountDAO;
import Repositories.AccountDAOImpl;
import Repositories.EntityManagerUtil;
import ResponseObjects.ErrorAccountResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Path("/accounts")
public class AccountController {
    private static final Logger LOGGER = LogManager.getLogger(AccountController.class);

    @GET
    public String sayHello() {
        return "Hi, Mariel";
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAccount(AccountDTO accountDTO) {
        EntityManager em = EntityManagerUtil.createEntityManager();

        try {
            AccountDAO accountDAO = new AccountDAOImpl(em);
            Account accountFoundInDatabase = accountDAO.getByNameOrIban(mapAccountDTO(accountDTO));

            //Account not found in database. Create new account
            if (accountFoundInDatabase == null) {
                Account newAccount = accountDAO.saveAccount(mapAccountDTO(accountDTO));
                LOGGER.info("Account created successfully: {}", newAccount.getId());
                return Response.created(UriBuilder.fromPath("/api/accounts/" + newAccount.getId()).build()).build(); //201

                //Account found in database
            } else {
                //Avoid duplicates
                if (accountNameAndIbanAlreadyExist(accountDTO, accountFoundInDatabase)) {
                    LOGGER.warn("Avoid duplicate for name: {}, iban: {}", accountDTO.getName(), accountDTO.getIBAN());
                    ErrorAccountResponse er = new ErrorAccountResponse(
                            "There already exists an account with name " + accountDTO.getName() +
                                    " and iban " + accountDTO.getIBAN());
                    return Response.status(Response.Status.BAD_REQUEST).entity(er).build();
                    //update account name based on iban
                    //account name does not exist in database
                } else if (accountNameIsNullOrEmpty(accountFoundInDatabase)) {
                    boolean isUpdated = accountDAO.updateAccount(mapAccountDTO(accountDTO));
                    if (isUpdated) {
                        return Response.status(Response.Status.NO_CONTENT).entity("").build();
                    } else {
                        LOGGER.warn("Something went wrong and no account was updated");
                        ErrorAccountResponse er = new ErrorAccountResponse("Something went and no account was updated!");
                        return Response.status(Response.Status.BAD_REQUEST).entity(er).build();
                    }
                    //account name exists in database but with another iban
                } else {
                    LOGGER.warn("There already exists an account with iban: {} with another name: {}",
                            accountDTO.getIBAN(), accountDTO.getName());
                    ErrorAccountResponse er = new ErrorAccountResponse("There already exists an account with iban " +
                            accountDTO.getIBAN() + " for a different account.");
                    return Response.status(Response.Status.BAD_REQUEST).entity(er).build();
                }
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    private boolean accountNameIsNullOrEmpty(Account accountFoundInDatabase) {
        return accountFoundInDatabase.getName().isBlank() ||
                accountFoundInDatabase.getName().isEmpty() ||
                accountFoundInDatabase.getName() == null;
    }

    private boolean accountNameAndIbanAlreadyExist(AccountDTO accountDTO, Account accountFoundInDatabase) {
        if (accountFoundInDatabase.getName().equals(accountDTO.getName())) {
            return accountFoundInDatabase.getIban().equals(accountDTO.getIBAN());
        }
        return false;
    }

    private Account mapAccountDTO(AccountDTO accountDTO) {
        Entities.Account account = new Entities.Account();
        account.setIban(accountDTO.getIBAN());
        account.setName(accountDTO.getName());
        //TODO: set payments for an account
        //account.setPayments(accountDTO.getPayments());
        return account;
    }
}