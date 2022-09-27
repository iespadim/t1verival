public class CentroDistribuicao {
    public enum SITUACAO {
        NORMAL, SOBRAVISO, EMERGENCIA
    }

    public enum TIPOPOSTO {
        COMUM, ESTRATEGICO
    }

    public static final int MAX_ADITIVO = 500;
    public static final int MAX_ALCOOL = 2500;
    public static final int MAX_GASOLINA = 10000;

    //O método construtor recebe as quantidades iniciais de gasolina nos tanques e ajusta a “situação” de acordo. Caso algum dos parâmetros tenha valor inválido o método deve gerar uma ILLEGAL_ARGUMENT_EXCEPTION (isso vale também para quantidades iniciais de álcool que devem ser iguais).
    public CentroDistribuicao(int tAditivo, int tGasolina, int tAlcool1, int tAlcool2) {
    }

    public void defineSituacao() {
    }

    public SITUACAO getSituacao() {
    }

    public int gettGasolina() {
    }

    public int gettAditivo() {
    }

    public int gettAlcool1() {
    }

    public int gettAlcool2() {
    }

    //Os métodos “recebeAditivo”, “recebeGasolina” e “recebeAlcool” são usados quando o centro de distribuição recebe carga dos componentes. Todos recebem por parâmetro a quantidade do componente (aditivo, gasolina ou álcool) recebida e retornam à quantidade que puderam armazenar devido a limitação do tamanho dos tanques e de quanto ainda tinham armazenado. Devem retornar “-1” caso a quantidade recebida por parâmetro seja inválida.
    public int recebeAditivo(int qtdade) {
    }

    public int recebeGasolina(int qtdade) {
    }

    public int recebeAlcool(int qtdade) {
    }

    
    public int[] encomendaCombustivel(int qtdade, TIPOPOSTO tipoPosto) {
    }
}