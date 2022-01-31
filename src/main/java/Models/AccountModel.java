package Models;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountModel {

    private String accountName;
    private String creditCardNumber;
    private String balance;

/*    @JsonIgnore
    private WebElement accountLink;*/

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AccountModel)) {
            return false;
        }
        AccountModel that = (AccountModel) o;
        return accountName.equals(that.accountName) && balance.equals(that.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountName, balance);
    }
}
