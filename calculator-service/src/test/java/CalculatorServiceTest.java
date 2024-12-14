import com.example.configs.CalculatorConfig;
import com.example.service.impl.CalculatorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

//UNIT TESTS

@ExtendWith(SpringExtension.class)
@EnableConfigurationProperties(value = CalculatorConfig.class)
@TestPropertySource("classpath:test-application.properties")
public class CalculatorServiceTest {

    private CalculatorService calculatorService;

    @Autowired
    private CalculatorConfig calculatorConfig;

    private  BigDecimal zero;
    private  BigDecimal two;
    private  BigDecimal three;
    private  int precision;

    @BeforeEach
    public void setUp() {
        this.calculatorService = new CalculatorService(calculatorConfig);
        this.zero = new BigDecimal(0);
        this.two = new BigDecimal(2);
        this.three = new BigDecimal(3);
        this.precision = 4;
    }

    @Test
    public void testSum(){

        assertThat(calculatorService.sum(two,two, precision)).isEqualTo(BigDecimal.valueOf(4));
    }

    @Test
    public void testSubtraction(){
        assertThat(calculatorService.subtraction(two,two,precision)).isEqualTo(BigDecimal.valueOf(0));
    }

    @Test
    public void testMultiplication(){
        assertThat(calculatorService.multiplication(two,three, precision)).isEqualTo(BigDecimal.valueOf(6));
    }

    @Test
    public void testDivision(){
        assertThat(calculatorService.division(two, two, precision)).isEqualTo(BigDecimal.valueOf(1));
    }

    @Test
    public void testDivisionByZero(){
        Assertions.assertThrows(ArithmeticException.class,()-> calculatorService.division(two, zero, precision));

    }

    @Test
    public void testArbitraryPrecision (){
        assertThat(calculatorService.division(two, three, precision).precision()).isEqualTo(precision);

    }

    @Test
    public void testArbitraryPrecisionDefaultConfig (){
        assertThat(calculatorService.division(two, three,0).precision()).isEqualTo(calculatorConfig.getDefaultPrecision());

    }
}
