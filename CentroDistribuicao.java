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

    public SITUACAO situacao;
    public int tAditivo;
    public int tGasolina;
    public int tAlcool1;
    public int tAlcool2;


    //O método construtor recebe as quantidades iniciais de gasolina nos tanques e ajus
    //ta a “situação” de acordo. Caso algum dos parâmetros tenha valor inválido o método 
    //deve gerar uma ILLEGAL_ARGUMENT_EXCEPTION (isso vale também para quantidades iniciais
    // de álcool que devem ser iguais).
    public CentroDistribuicao(int tAditivo, int tGasolina, int tAlcool1, int tAlcool2) {
    }

    //O método “defineSituacao” ajusta a situação de acordo com as regras. Ele deve ser 
    // chamado tanto pelos métodos que sinalizam a chegada de componentes no centro de 
    // distribuição quanto pelo método “encomendaCombustivel” que sinaliza o fornecimento 
    // de combustível para um posto.
    public void defineSituacao() {
    }

    public SITUACAO getSituacao() {
        return situacao;
    }

    public int gettGasolina() {
        return tGasolina;
    }

    public int gettAditivo() {
        return tAditivo;
    }

    public int gettAlcool1() {
        return tAlcool1;
    }

    public int gettAlcool2() {
        return tAlcool2;
    }

    //Os métodos “recebeAditivo”, “recebeGasolina” e “recebeAlcool” são usados quando
    // o centro de distribuição recebe carga dos componentes. Todos recebem por parâme
    //tro a quantidade do componente (aditivo, gasolina ou álcool) recebida e retornam
    // à quantidade que puderam armazenar devido a limitação do tamanho dos tanques e 
    //de quanto ainda tinham armazenado. Devem retornar “-1” caso a quantidade recebida
    // por parâmetro seja inválida.
    public int recebeAditivo(int qtdade) {
        //TODO
        return 0;
    }

    public int recebeGasolina(int qtdade) {
        //TODO
        return 0;
    }

    public int recebeAlcool(int qtdade) {
        //TODO
        return 0;
    }

    //O método “encomendaCombustivel” é usado quando o centro de distribuição recebe o 
    // pedido de um posto. Este método recebe por parâmetro a quantidade solicitada pelo 
    // posto e o tipo do posto. Se o pedido puder ser atendido, o método retorna um arranjo
    // com a quantidade de combustível remanescente em cada tanque, depois do pedido atendido.
    // As quantidades devem ser retornadas pela ordem: aditivo, gasolina, álcool T1 e álcool T2. 
    // A primeira posição do arranjo é usada também para indicar códigos de erro. No caso de ser 
    // recebido um valor inválido por parâmetro deve-se retornar “-7” na primeira posição do arranjo, 
    // se o pedido não puder ser atendido em função da “situação” retorna-se “-14” e, caso não haja
    // combustível suficiente para completar a mistura, retorna-se “-21”. 
    // Por simplicidade trabalha-se apenas com números inteiros.
    // Na hora de fazer os cálculos multiplique os valores por 100. 
    // Depois de feitos os cálculos dividam por 100 novamente e despreze a parte fracionária;
    public int[] encomendaCombustivel(int qtdade, TIPOPOSTO tipoPosto) {
        int[] result = new int[4];
        
        //TODO
        return result;
    }
}