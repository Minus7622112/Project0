package loan;

import org.junit.jupiter.api.Test;
import revature.pro0.dao.LoanDao;
import revature.pro0.model.Loan;
import revature.pro0.service.LoanService;
import static org.junit.jupiter.api.Assertions.*;

public class LoanServiceTest {

    @Test
    void shouldAddLoan(){
    LoanService service = new LoanService(new LoanDao("", "", ""));

    service.addLoan(new Loan());

    assertEquals(1, service.getAllLoans().size(), "Should have more than 1");
    assertEquals("Loan", service.getAllLoans().get(0).getLoan_name());

    }
}