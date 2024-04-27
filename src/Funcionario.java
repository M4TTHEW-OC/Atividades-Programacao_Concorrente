public class Funcionario extends Thread{

    private String nomeFuncionario;
    private Loja loja;
    private Conta SalarioFuncionario;
    private Conta InvestimentoFuncionario;
    private Object monitor;

    public Funcionario(String nome, Loja loja, Object monitor) {
        this.nomeFuncionario = nome;
        this.loja = loja;
        this.SalarioFuncionario = new Conta(loja.getConta().getbanco());
        this.InvestimentoFuncionario = new Conta(loja.getConta().getbanco());
        this.monitor = monitor;
    }

    public void run() {
        double remuneracao = 1400.0;
        synchronized (loja.getConta().getbanco()) {
            if (loja.getConta().getSaldo() >= remuneracao) {
                loja.getConta().saque(remuneracao);
                getContaSalario().depositar(remuneracao);
                
                // Cálculo do investimento (20% do salário)
                
                double valorInvestimento = remuneracao * 0.2;
                getContaInvestimento().depositar(valorInvestimento);
                double saldoSalarioFinal = getContaSalario().getSaldo() - valorInvestimento;
                System.out.println("---");
                System.out.println(nomeFuncionario + " recebeu R$" + remuneracao + " da " + loja.getNome());
                System.out.println(nomeFuncionario + " investiu R$" + valorInvestimento);
                System.out.println("saldo do " + nomeFuncionario + " após investimento: R$" + saldoSalarioFinal);
            }
        }
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    public Conta getContaSalario() {
        return SalarioFuncionario;
    }

    public Conta getContaInvestimento() {
        return InvestimentoFuncionario;
    }
}