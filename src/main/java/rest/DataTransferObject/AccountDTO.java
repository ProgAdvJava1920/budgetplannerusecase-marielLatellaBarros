package DataTransferObject;

public class AccountDTO {

    private String iban;
    private String name;

    public String getIBAN() {
        return this.iban;
    }

    public String getName() {
        return this.name;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public void setName(String name) {
        this.name = name;
    }
}
