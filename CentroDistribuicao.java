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
        if (tAditivo > MAX_ADITIVO)     throw new IllegalArgumentException();
        if (tGasolina > MAX_GASOLINA)   throw new IllegalArgumentException();
        if (tAlcool1 != tAlcool2)       throw new IllegalArgumentException();
        if ((tAlcool1 != tAlcool2) & (tAlcool1+tAlcool2 >MAX_ALCOOL))
                                        throw new IllegalArgumentException();

        this.tAditivo = tAditivo;
        this.tGasolina = tGasolina;
        this.tAlcool1 = tAlcool1;
        this.tAlcool2 = tAlcool2;

        defineSituacao();
    }

    //O método “defineSituacao” ajusta a situação de acordo com as regras. Ele deve ser 
    // chamado tanto pelos métodos que sinalizam a chegada de componentes no centro de 
    // distribuição quanto pelo método “encomendaCombustivel” que sinaliza o fornecimento 
    // de combustível para um posto.
    // Quando todos os tanques estiverem com pelo menos 50% da capacidade o sistema opera
    // em modo NORMAL
    // Se o volume armazenado em qualquer um dos tanques cair abaixo de 50% o sistema passa a
    // operar em modo SOBRAVISO.
    // Caso o volume em qualquer um dos tanques caia abaixo de 25%, então o sistema passa a
    // operar em modo de EMERGÊNCIA
    public void defineSituacao() {
        double gasolina = gettGasolina()*1.0;
        double aditivo  = gettAditivo()*1.0;
        double alcool   = (gettAlcool1() + gettAlcool2())*1.0;

        double percentGasolina =  (gasolina / MAX_GASOLINA);
        double percentAditivo  =  (aditivo/MAX_ADITIVO);
        double percentAlcool   =  (alcool/MAX_ALCOOL);

        double temp = percentGasolina < percentAlcool ? percentGasolina : percentAlcool;
        double percent = percentAditivo < temp ? percentAditivo : temp;

        if (percent <0.25){
           this.situacao = SITUACAO.EMERGENCIA;
        } else if (percent >= 0.25 & percent <0.5){
            this.situacao = SITUACAO.SOBRAVISO;
        }else{
            this.situacao = SITUACAO.NORMAL;
        }
    }

    public SITUACAO getSituacao() {
        defineSituacao();
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

    // Os métodos “recebeAditivo”, “recebeGasolina” e “recebeAlcool” são usados quando
    // o centro de distribuição recebe carga dos componentes. Todos recebem por parâmetro
    // a quantidade do componente (aditivo, gasolina ou álcool) recebida e retornam
    // à quantidade que puderam armazenar devido a limitação do tamanho dos tanques e 
    // de quanto ainda tinham armazenado. Devem retornar “-1” caso a quantidade recebida
    // por parâmetro seja inválida.
    public int recebeAditivo(int qtdade) {
        int result = 0;
        if((gettAditivo() + qtdade) > MAX_ADITIVO){
            //overflow
            int total = gettAditivo() + qtdade;
            tAditivo = MAX_ADITIVO;
            result= total - MAX_ADITIVO;
        }
        if (gettAditivo()+qtdade <=MAX_ADITIVO){
            tAditivo+=qtdade;
            result = 0;
        }
        if (qtdade <0){
            result = -1;
        }
        defineSituacao();
        return result;
    }

    public int recebeGasolina(int qtdade) {
        int result = 0;
        if((gettGasolina() + qtdade) > MAX_GASOLINA){
            //overflow
            int total = gettGasolina() + qtdade;
            tGasolina = MAX_GASOLINA;
            result= total - MAX_GASOLINA;
        }
        if (gettGasolina()+qtdade <=MAX_GASOLINA){
            tGasolina+=qtdade;
            result = 0;
        }
        if (qtdade <0){
            result = -1;
        }
        defineSituacao();
        return result;
    }

    public int recebeAlcool(int qtdade) {
        int result = 0;
        if((gettAlcool1()  + gettAlcool2() + qtdade) > MAX_ALCOOL){
            int total = gettAlcool1()  + gettAlcool2() + qtdade;
            tAlcool1 = MAX_ALCOOL/2;
            tAlcool2 = MAX_ALCOOL/2;

            result= total - MAX_ALCOOL;
        }
        if (gettAlcool1()  + gettAlcool2() + qtdade <=MAX_ALCOOL){
            tAlcool1+=qtdade/2;
            tAlcool2+=qtdade/2;
            result = 0;
        }
        if (qtdade <0){
            result = -1;
        }
        defineSituacao();
        return result;
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

    //a gasolina vendida nos postos é resultado de uma mistura de 3 componentes:
    // 5% de aditivo, 25% de álcool e 70% de gasolina pura
    public int[] encomendaCombustivel(int qtdade, TIPOPOSTO tipoPosto) {
        defineSituacao();

        int[] result = new int[5];
        int[] estoque = new int[5];

        estoque[1]=gettAditivo();
        estoque[2]=gettGasolina();
        estoque[3]=gettAlcool1();
        estoque[4]=gettAlcool2();

        double uso_alcool = (qtdade * 0.25);
        double uso_gasolinaPura = (qtdade * 0.7);
        double uso_aditivo= (qtdade * 0.05);

        // recebido um valor inválido por parâmetro deve-se retornar “-7” na primeira posição do arranjo,
        if (qtdade <0){
            System.out.println("-7");
            result[0]=-7;
            result[1]=gettAditivo();
            result[2]=gettGasolina();
            result[3]=gettAlcool1();
            result[4]=gettAlcool2();
            return result;
        }

        // se o pedido não puder ser atendido em função da “situação” retorna-se “-14”
        if (getSituacao()==SITUACAO.EMERGENCIA && tipoPosto ==TIPOPOSTO.COMUM){
            System.out.println("-14");
            result[0]=-14;
            result[1]=gettAditivo();
            result[2]=gettGasolina();
            result[3]=gettAlcool1();
            result[4]=gettAlcool2();
            return result;
        }

        //em modo de EMERGÊNCIA e as encomendas dos postos ESTRATÉGICOS são atendidas em 50%.
        if (getSituacao()==SITUACAO.EMERGENCIA && tipoPosto ==TIPOPOSTO.ESTRATEGICO){
            System.out.println("emergencia estrategico");
            result[0]=0;
            result[1]=gettAditivo() - toInt(uso_aditivo/2);
            result[2]=gettGasolina()- toInt(uso_gasolinaPura/2);
            result[3]=gettAlcool1() - toInt(uso_alcool/4);
            result[4]=gettAlcool2() - toInt(uso_alcool/4);
        }

        //em modo de SOBRAVISO e as encomendas dos postos COMUMS são atendidas em 50%.
        if (getSituacao()==SITUACAO.SOBRAVISO && tipoPosto ==TIPOPOSTO.COMUM){
            System.out.println("sobraviso comum");
            result[0]=0;
            result[1]=gettAditivo() - toInt(uso_aditivo/2);
            result[2]=gettGasolina()- toInt(uso_gasolinaPura/2);
            result[3]=gettAlcool1() - toInt(uso_alcool/4);
            result[4]=gettAlcool2() - toInt(uso_alcool/4);
        }

        //em modo de SOBRAVISO e as encomendas dos postos ESTRATÉGICOS são atendidas em 100%.
        //em modo de NORMAL e as encomendas de todos postos sao atentidas em 100%.
        if((getSituacao()==SITUACAO.SOBRAVISO && tipoPosto ==TIPOPOSTO.ESTRATEGICO) |
                getSituacao()==SITUACAO.NORMAL){
            result[0]=0;
            result[1]=gettAditivo() - toInt(uso_aditivo);
            result[2]=gettGasolina()- toInt(uso_gasolinaPura);
            result[3]=gettAlcool1() - toInt(uso_alcool/2);
            result[4]=gettAlcool2() - toInt(uso_alcool/2);
        }


        //  caso não haja combustível suficiente para completar a mistura, retorna-se “-21”
        for (int i=1;i<=4;i++){
            if (result[i] <0){
                result[0]=-21;
            }
        }

        // se o resultado for -21, retorna o estoque atual e não o resultado esperado
        if(result[0]==-21){
            result[1]=estoque[1];
            result[2]=estoque[2];
            result[3]=estoque[3];
            result[4]=estoque[4];
        }else{
            tAditivo=result[1];
            tGasolina=result[2];
            tAlcool1=result[3];
            tAlcool2=result[4];
        }
        defineSituacao();
        return result;
    }

    public int toInt(double valor, int percent){
        double calc = ((valor * 100) * percent ) / 100;
        return (int) calc;
    }

    public int toInt(double valor){
        System.out.println("toint valor "+valor);
        double calc = ((valor * 100)) / 100;
        System.out.println((int) calc);
        return (int) calc;
    }

    public int[] getTanques(){
        int[] estoque = new int[5];

        estoque[1]=gettAditivo();
        estoque[2]=gettGasolina();
        estoque[3]=gettAlcool1();
        estoque[4]=gettAlcool2();

        return estoque;
    }
}