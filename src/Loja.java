
public class Loja {

    private String nome;
    private Conta contaLoja;
    private Object monitor;

    public Loja(String nome, Banco banco, Object monitor) {
        this.nome = nome;
        this.contaLoja = new Conta(banco);
        this.monitor = monitor;
    }

    public String getNome() {
        return nome;
    }

    public Conta getConta() {
        return contaLoja;
    }
}