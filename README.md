## Create unit tests in `service.CalculatorImplTest` class

**Requirements for this test class:**

- be sure to create method `beforeAll` with annotation `BeforeAll` for initialize some common object for all tests.

- create parametrized test `isNonNegativeTest` for `service.CalculatorImpl.isNonNegative` method
 where the parameters for test pass through the values of ValueSource annotation
 
- create  parametrized test `sumTest` for `service.CalculatorImpl.sum` method,
 where test data defined in method `sumTestProvider` of this test class.