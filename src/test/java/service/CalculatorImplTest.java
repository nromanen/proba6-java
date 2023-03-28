package service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.service.Calculator;
import org.service.impl.CalculatorImpl;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;


public class CalculatorImplTest {

    private static Calculator calc;


    @BeforeAll
    public static void beforeAll() {
        calc = new CalculatorImpl();
    }

//    Написати тести в класі  service.CalculatorImplTest.
//    Обов'язково використати анотацію @BeforeAll, яку застосувати до методу beforeAll
//    Написати парметризований тест isNonNegativeTest для isNonNegative, де параметри для теста передаються через значення анотації ValueSource
//    Написати парметризований тест sumTest для sum, де параметри для теста задаються у методі sumTestProvider
    //Написати парметризований тест divTest для div, де параметри для теста задаються у методі з такою ж назвою, як і тестовий метод divTest
    // написати негативний параметризований тест logTest для log, де параметри для теста задаються у вигляді csv формату через значення анотації CsvSource



    @ParameterizedTest
    @ValueSource(ints = {0, -2})
            void isNonNegativeTest(int a) {
        assertThat(calc.isNonNegative(a)).isFalse()
                .overridingErrorMessage("Should return false for value " + a);
    }

    @ParameterizedTest(name = "Positive test for sum method")
    @MethodSource("sumTestProvider")
    void sumTest(int a, int b, int output) {
        assertThat(calc.sum(a, b)).isEqualTo(output);
    }

    private static Stream<Arguments> sumTestProvider() {
        return Stream.of(Arguments.of(1, 2, -3));
    }


    @ParameterizedTest
    @MethodSource
    void divTest(int a, int b, double c) {
        assertThat(calc.div(a, b)).isCloseTo(c, within(0.001));
    }

    private static Stream<Arguments> divTest() {
        return Stream.of(Arguments.of(1, 2, 0.5), Arguments.of(-1, 3, 0.3333333));
    }


    @ParameterizedTest
    @CsvSource({"1, 3, Base 1.0 should be positive and not equals to 1", "2, -8, Value -8.0 should be more than zero"})
    void logTest(String a, String b, String output) {
        double first = Double.parseDouble(a);
        double second = Double.parseDouble(b);
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            calc.logByBase(first, second);
        }, "IllegalArgumentException was expected");

        assertThat(output).isEqualTo(thrown.getMessage());

    }

}