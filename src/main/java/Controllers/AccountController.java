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
    public String sayHello(){
        return "Hi, Mariel";
    }
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAccount(AccountDTO accountDTO) {
        EntityManager em = EntityManagerUtil.createEntityManager();

            try {
                AccountDAO accountDAO = new AccountDAOImpl(em);
                Account accountFoundByName = accountDAO.getByName(accountDTO.getName());
                Account accountFoundByIban = accountDAO.getByIban(accountDTO.getIBAN());

                if (accountFoundByIban.getIban().equalsIgnoreCase(accountDTO.getIBAN()) &&
                        (accountFoundByIban.getName() == null) || accountFoundByIban.getName().equalsIgnoreCase("")) {
                    LOGGER.warn("Fill in the account holder name for this iban {}", accountDTO.getIBAN());
                    ErrorAccountResponse er = new ErrorAccountResponse("Fill in the account holder name for this iban: " +
                            accountDTO.getIBAN());
                    return Response.status(Response.Status.BAD_REQUEST).entity(er).build();

                } else if (accountFoundByName != null) {
                    LOGGER.warn("There already exists an account with name: {}", accountDTO.getName());
                    ErrorAccountResponse er = new ErrorAccountResponse("There already exists an account with name " + accountDTO.getName());
                    return Response.status(Response.Status.BAD_REQUEST).entity(er).build();
                } else {
                    Account newAccount = accountDAO.saveAccount(mapAccountDTO(accountDTO));
                    if (newAccount != null) {
                        LOGGER.info("Account created successfully: {}", newAccount.getId());
                        return Response.created(UriBuilder.fromPath("/api/accounts/" + newAccount.getId()).build()).build(); //201
                    } else {
                        LOGGER.warn("Something went wrong and no account was created");
                        ErrorAccountResponse er = new ErrorAccountResponse("Something went wrong!");
                        return Response.status(Response.Status.BAD_REQUEST).entity(er).build();
                    }
                }
            } finally {
                if (em != null) {
                    em.close();
                }
            }
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