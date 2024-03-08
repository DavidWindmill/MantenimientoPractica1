package bank;
/*
  Test cases
  1. factorial 0 -> 1
  2. factorial 1 -> 1
*/

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    /*
    Factorial factorial  ;

    @BeforeEach
    void setup() {
        factorial = new Factorial() ;
    }
     */

  @ParameterizedTest
  @DisplayName("Comprueba si se saca la cantidad correcta")
  @CsvSource({
  "500, 150, 350"})
  void withdrawMoneyCorrectlyFromAccount(int moneyInAccount, int amountToWithdraw, int expectedAmount) {
    BankAccount bankAccount = new BankAccount(moneyInAccount);
    
    bankAccount.withdraw(amountToWithdraw);

    assertEquals(expectedAmount, bankAccount.getBalance()) ;
  }

  @ParameterizedTest
  @DisplayName("Da False si no hay suficiente dinero para sacar de la cuenta")
  @CsvSource({
  "500, 150, True",
  "0, 10, False"})
  void IsAvailableToWithdraw(int moneyInAccount, int amountToWithdraw, Boolean expectedValue) {
    BankAccount bankAccount = new BankAccount(moneyInAccount);
    
    Boolean canWithdraw = bankAccount.withdraw(amountToWithdraw);

    assertEquals(expectedValue, canWithdraw) ;
  }

  @Test
  @DisplayName("Comprueba que no se puede sacar dinero negativo")
  void WithdrawNegativeAmountException() {
    BankAccount bankAccount = new BankAccount(500);

    assertThrows(IllegalArgumentException.class, () -> {
      bankAccount.withdraw(-100);
    }) ;
  }

  @ParameterizedTest
  @DisplayName("Comprueba si se deposita dinero en la cuenta")
  @CsvSource({
  "500, 150, 650",
  "0, 0, 0"})
  void DepositCorrectlyInAccount(int moneyInAccount, int amountDeposited, int expectedAmount) {
    BankAccount bankAccount = new BankAccount(moneyInAccount);
    
    int totalAmount = bankAccount.deposit(amountDeposited);

    assertEquals(expectedAmount, totalAmount) ;
  }

  @Test
  @DisplayName("Comprueba que no se puede depositar dinero negativo")
  void DepositedNegativeAmountException() {
    BankAccount bankAccount = new BankAccount(500);

    assertThrows(IllegalArgumentException.class, () -> {
      bankAccount.deposit(-100);
    }) ;
  }


  @ParameterizedTest
  @DisplayName("Me devuelve el balance de la cuenta")
  @CsvSource({
  "300",
  "0",
  "-200"})
  void GetBalanceCorresctOutputTest(int balance) {
    BankAccount bankAccount = new BankAccount(balance);

    int totalBalance = bankAccount.getBalance();

    assertEquals(balance, totalBalance) ;
  }

  @ParameterizedTest
  @DisplayName("Comprueba si se hace correctamente el pago")
  @CsvSource({
  "100, 0.1, 5, 26.379"
  })
  void PaymentCorresctOutputTest(double total_amount, double interest, int npayments, double expectedPayment) {
    BankAccount bankAccount = new BankAccount(0);

    double totalPayment = bankAccount.payment(total_amount, interest, npayments);

    assertTrue(expectedPayment < totalPayment+0.001 && expectedPayment > totalPayment - 0.001) ;
  }

  @ParameterizedTest
  @DisplayName("Comprueba que no se pueden meter numeros negativos en el pago")
  @CsvSource({
  "-100, 0.1, 5",
  "100, -0.1, 5",
  "100, 0.1, -5"
  })
  void PaymentNegativeNumbersException(double total_amount, double interest, int npayments) {
    BankAccount bankAccount = new BankAccount(500);

    assertThrows(IllegalArgumentException.class, () -> {
      bankAccount.payment(total_amount, interest, npayments);
    }) ;
  }

  @ParameterizedTest
  @DisplayName("Comprueba que hace la funcion pending correctamente")
  @CsvSource({
  "100, 0.1, 5, 12, -250.269",
  "100, 0.1, 5, 0, 100"
  })
  void PendingCorresctOutputTest(double amount, double inte, int npayments, int month, double expectedPending) {
    BankAccount bankAccount = new BankAccount(0);

    double totalPayment = bankAccount.pending(amount, inte, npayments, month);

    assertTrue(expectedPending < totalPayment+0.001 && expectedPending > totalPayment - 0.001) ;
  }

  @ParameterizedTest
  @DisplayName("Comprueba que no se pueden meter valores negativos en la funcion pending")
  @CsvSource({
  "-100, 0.1, 5, 12",
  "100, -0.1, 5, 12",
  "100, 0.1, -5, 12",
  "100, 0.1, 5, -12"
  })
  void PendingNegativeNumbersException(double amount, double inte, int npayments, int month) {
    BankAccount bankAccount = new BankAccount(500);

    assertThrows(IllegalArgumentException.class, () -> {
      bankAccount.pending(amount, inte, npayments, month);
    }) ;
  }

}
