import java.util.List;
import java.util.Random;

public class Cliente extends Thread{

    private String nome_cliente;
    private Conta conta_cliente;
    private List<Loja> lojas;
    private Object monitor;

    public Cliente(String nome, Banco banco, Loja loja1, Loja loja2, Object monitor) {
        this.nome_cliente = nome;
        this.conta_cliente = new Conta(banco, 1000.0);
        this.lojas = List.of(loja1, loja2);
        this.monitor = monitor;
    }

    public void run() {
        Random random = new Random();
        while (conta_cliente.getSaldo() > 0) {
            synchronized (monitor) {
                // Seleciona aleatoriamente uma loja para compra
                Loja loja = lojas.get(random.nextInt(lojas.size()));
                // Define o valor da compra (100 ou 200 reais)
                double valor = random.nextBoolean() ? 100.0 : 200.0;
                if (conta_cliente.getSaldo() >= valor) {
                    loja.getConta().getbanco().transferir(this, nome_cliente, valor, loja);
                    System.out.println("Saldo atual: R$" + conta_cliente.getSaldo());
                }
            }
        }
    }

    public Conta getConta() {
        return conta_cliente;
    }
}