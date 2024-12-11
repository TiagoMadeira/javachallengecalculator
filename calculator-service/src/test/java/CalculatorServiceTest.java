import com.example.CalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

//UNIT TESTS
public class CalculatorServiceTest {

    private CalculatorService calculatorService;

    @BeforeEach
    public void setUp() {
        this.calculatorService = new CalculatorService();
    }

    @Test
    public void testSum(){
        assertThat(calculatorService.sum(2,2)).isEqualTo(4);
    }

    @Test
    public void testSubtraction(){
        assertThat(calculatorService.subtraction(2,2)).isEqualTo(0);
    }

    @Test
    public void testMultiplication(){
        assertThat(calculatorService.multiplication(2,3)).isEqualTo(6);
    }

    @Test
    public void testDivision(){
        assertThat(calculatorService.division(2, 2)).isEqualTo(1);
    }

}
