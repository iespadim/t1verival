import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class Driver {
    public static void main(String[] args) {
        CentroDistribuicao centroVazio =
                new CentroDistribuicao(0,0,0,0);

        CentroDistribuicao centroEmergencia=
                new CentroDistribuicao(
                        120,2000,120,120);

        CentroDistribuicao centroNormal=
                new CentroDistribuicao(
                        CentroDistribuicao.MAX_ADITIVO,
                        CentroDistribuicao.MAX_GASOLINA,
                        CentroDistribuicao.MAX_ALCOOL/2,
                        CentroDistribuicao.MAX_ALCOOL/2);

        CentroDistribuicao centroSobraviso=
                new CentroDistribuicao(
                        CentroDistribuicao.MAX_ADITIVO/2-1,
                        CentroDistribuicao.MAX_GASOLINA/2-1,
                        CentroDistribuicao.MAX_ALCOOL/4-1,
                        CentroDistribuicao.MAX_ALCOOL/4-1);

        System.out.println(centroNormal.getSituacao());
        System.out.println(centroSobraviso.getSituacao());
        System.out.println(centroEmergencia.getSituacao());

        System.out.println(centroSobraviso.getSituacao());
        for (int i:centroSobraviso.getTanques()) {
            System.out.println(i);
        }
        centroSobraviso.encomendaCombustivel(-1, CentroDistribuicao.TIPOPOSTO.ESTRATEGICO);
        System.out.println(centroSobraviso.getSituacao());
        for (int i:centroSobraviso.getTanques()) {
            System.out.println(i);
        }
    }

    //parametrized test case, using csv source
    @ParameterizedTest
    @CsvSource({
            "1200   , COMUM"      ,
            "10000  , ESTRATEGICO",
            "-1, ESTRATEGICO",
    })
    public void testaEncomendaCombustivel(String qt, CentroDistribuicao.TIPOPOSTO tipoposto) {
        CentroDistribuicao centroNormal=
                new CentroDistribuicao(
                        CentroDistribuicao.MAX_ADITIVO,
                        CentroDistribuicao.MAX_GASOLINA,
                        CentroDistribuicao.MAX_ALCOOL/2,
                        CentroDistribuicao.MAX_ALCOOL/2);

        int[] result = centroNormal.encomendaCombustivel(Integer.parseInt(qt), tipoposto);

        //print int[] result
        System.out.println("result[]: ");
        for (int i:result) {
            System.out.print(i+" ");
        }

        Assertions.assertEquals(0, result[0]);
    }

    @ParameterizedTest
    @CsvSource({
            "0,0,0,EMERGENCIA",
            "249,2499,625,EMERGENCIA",
            "250,2500,626,SOBRAVISO",
            "499,4999,1250,SOBRAVISO",
            "500,10000,2500,NORMAL",
    })

    public void testaAbastecePostoValorLimite(int qtAditivo, int qtGasolina, int qtAlcool, CentroDistribuicao.SITUACAO situacaoEsp) {
        CentroDistribuicao centroVazio=
                new CentroDistribuicao(0,0,0,0);

        centroVazio.recebeAditivo(qtAditivo);
        centroVazio.recebeGasolina(qtGasolina);
        centroVazio.recebeAlcool(qtAlcool);

        for (int i = 0; i < centroVazio.getTanques().length ; i++) {
            System.out.println(centroVazio.getTanques()[i]);
        }

        Assertions.assertEquals(situacaoEsp, centroVazio.getSituacao());

    }


}
