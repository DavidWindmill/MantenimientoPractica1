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
  @DisplayName("Comprueba si se quita la cantidad correcta")
  @CsvSource({
  "500, 150, 350",
  "500, -100, 600"})
  void withdrawMoney(int moneyInAccount, int amountToWithdraw, int expectedAmount) {
    BankAccount bankAccount = new BankAccount(moneyInAccount);
    
    bankAccount.withdraw(amountToWithdraw);

    assertEquals(expectedAmount, bankAccount.getBalance()) ;
  }

  @ParameterizedTest
  @DisplayName("Da False si no hay suficiente dinero para quitar de la cuenta")
  @CsvSource({
  "500, 150, True",
  "500, -100, True",
  "0, 10, False"})
  void availableToWithdraw(int moneyInAccount, int amountToWithdraw, Boolean expectedValue) {
    BankAccount bankAccount = new BankAccount(moneyInAccount);
    
    Boolean canWithdraw = bankAccount.withdraw(amountToWithdraw);

    assertEquals(expectedValue, canWithdraw) ;
  }

  @ParameterizedTest
  @DisplayName("Comprueba si se deposita dinero en la cuenta")
  @CsvSource({
  "500, 150, 650",
  "0, 0, 0"})
  void availableToDeposit(int moneyInAccount, int amountDeposited, int expectedAmount) {
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

}
