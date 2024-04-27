public class Conta {
    private Banco banco;
    private double saldoConta;

    public Conta(Banco banco) {
        this.banco = banco;
        this.saldoConta = 0.0;
    }

    public Conta(Banco banco, double saldo) {
        this.banco = banco;
        this.saldoConta = saldo;
    }

    public synchronized double getSaldo() {
        return saldoConta;
    }

    public synchronized void depositar(double valor) {
    	saldoConta += valor;
    }

    public synchronized void saque(double valor) {
        if (saldoConta >= valor) {
        	saldoConta -= valor;
        }
    }

    public Banco getbanco() {
        return banco;
    }
}