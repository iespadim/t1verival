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

    public int recebeAditivo(int qtdade) {
    }

    public int recebeGasolina(int qtdade) {
    }

    public int recebeAlcool(int qtdade) {
    }

    public int[] encomendaCombustivel(int qtdade, TIPOPOSTO tipoPosto) {
    }
}