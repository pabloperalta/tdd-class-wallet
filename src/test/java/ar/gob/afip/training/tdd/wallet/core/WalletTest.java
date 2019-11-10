import ar.gob.afip.training.tdd.wallet.core.Wallet;
import ar.gob.afip.training.tdd.wallet.core.exception.WalletBusinessException;
import org.junit.jupiter.api.*;

import static org.fest.assertions.Assertions.assertThat;

public class WalletTest {

    private Wallet wallet;

    @BeforeEach
    void setUp() {
        this.wallet = new Wallet();
    }


    @Test
    void testGetBalanceReturns0_WhenGettingBalanceFromNewWallet() {
        // Given

        // When
        Double balance = this.wallet.getBalance();

        //Then
        assertThat(balance).isEqualTo(0);
    }

    @Test
    void testGetBalanceReturns100_WhenGettingBalanceOfWalletWith100() {
        wallet.setBalance(100d);

        Double balance = wallet.getBalance();

        assertThat(balance).isEqualTo(Double.valueOf(100));
    }

    @Test
    public void testWalletHas150Balance_WhenCredit150ToNewWallet() {
        wallet.credit(150d);

        Double balance = wallet.getBalance();

        assertThat(balance).isEqualTo(Double.valueOf(150));
    }

    @Test
    public void testWalletHas12345Balance_WhenCredit12345ToNewWallet() {
        wallet.credit(12345d);

        Double balance = wallet.getBalance();

        assertThat(balance).isEqualTo(Double.valueOf(12345));
    }

    @Test
    public void testWalletHas10Balance_WhenDebit90FromWalletWith100() throws WalletBusinessException {
        wallet.setBalance(100d);

        wallet.debit(90d);

        assertThat(wallet.getBalance()).isEqualTo(Double.valueOf(10));
    }

    @Test
    public void testWalletHas895Balance_WhenDebit105FromWalletWith1000() throws WalletBusinessException {
        wallet.setBalance(1000d);

        wallet.debit(105d);

        assertThat(wallet.getBalance()).isEqualTo(Double.valueOf(895));
    }


    @Test
    public void testDebit_ThrowsWalletBusinessExceptionWithUnsufficientFundsMessage_WhenDebiting1005AndBalanceIs1000() {
        // Given
        wallet.setBalance(1000d);

        try {
            wallet.debit(1005d);
            Assertions.fail();
        } catch (Exception e) {
            assertThat(e).isExactlyInstanceOf(WalletBusinessException.class);
            assertThat(e).hasMessage("No tenes saldo suficiente");
            assertThat(wallet.getBalance()).isEqualTo(Double.valueOf(1000));
        }
    }


    @Test
    public void testDebit_ThrowsWalletBusinessExceptionWithUnsufficientFundsMessage_WhenDebiting87AndBalanceIs40() {
        // Given
        wallet.setBalance(40d);

        try {
            wallet.debit(87d);
            Assertions.fail();
        } catch (Exception e) {
            assertThat(e).isExactlyInstanceOf(WalletBusinessException.class);
            assertThat(e).hasMessage("No tenes saldo suficiente");
            assertThat(wallet.getBalance()).isEqualTo(Double.valueOf(40));
        }
    }
}