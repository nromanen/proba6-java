package service;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import java.lang.reflect.Method;
import java.lang.reflect.Modifier;


public class MainTest {
    private static Class<?> valueSourceClass;

    @BeforeAll
    public static void beforeAll(){
        CalculatorImplTest c = new CalculatorImplTest();
        valueSourceClass = CalculatorImplTest.class;
    }

    @Test
    public void checkValueSource() throws NoSuchMethodException {
        Method m = valueSourceClass.getDeclaredMethod("isNonNegativeTest", int.class);
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(m.isAnnotationPresent(ParameterizedTest.class)).isTrue()
                .overridingErrorMessage("Method CalculatorImplTest.isNonNegativeTest must have ParameterizedTest annotation");
        softAssertions.assertThat(m.isAnnotationPresent(ValueSource.class)).isTrue()
                .overridingErrorMessage("Method CalculatorImplTest.isNonNegativeTest must have ValueSource annotation");
        softAssertions.assertAll();
    }

    @Test
    public void checkBeforeAll() throws NoSuchMethodException {
        Method m = valueSourceClass.getDeclaredMethod("beforeAll");
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(m.isAnnotationPresent(BeforeAll.class)).isTrue()
                .overridingErrorMessage("Method CalculatorImplTest.beforeAll must have BeforeAll annotation");
        softAssertions.assertThat(m.getReturnType()==void.class && Modifier.isStatic(m.getModifiers())).isTrue()
                .overridingErrorMessage("Method CalculatorImplTest.beforeAll must be static and void");
        softAssertions.assertAll();
    }


}
