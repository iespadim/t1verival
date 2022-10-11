import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import java.lang.reflect.Executable;

public class Driver {


    @ParameterizedTest
    @CsvSource({
            //qt combustivel, tipo posto, tanque inicial [tAdt, tGas, tAlc1, tAlc2], situação do tanque ao final [tAdt, tGas, tAlc1, tAlc2]
            // encomenda com sucesso, posto comum
            "1000   ,COMUM          ,500  ,10000  ,1250   ,1250   ,450    ,9300   ,1125   ,1125   ,0  ,NORMAL",
            "10000  ,COMUM          ,500  ,10000  ,1250   ,1250   ,0      ,3000   ,0      ,0      ,0  ,EMERGENCIA",
            "6000   ,COMUM          ,500  ,10000  ,1250   ,1250   ,200    ,5800   ,500    ,500    ,0  ,SOBRAVISO",
            // encomenda com sucesso, posto estrategico
            "1000   ,ESTRATEGICO    ,500  ,10000  ,1250   ,1250   ,450    ,9300   ,1125   ,1125   ,0  ,NORMAL",
            "10000  ,ESTRATEGICO    ,500  ,10000  ,1250   ,1250   ,0      ,3000   ,0      ,0      ,0  ,EMERGENCIA",
            "6000   ,ESTRATEGICO    ,500  ,10000  ,1250   ,1250   ,200    ,5800   ,500    ,500    ,0  ,SOBRAVISO",
            // encomenda com falha, posto comum
            "1000   ,COMUM          ,100  ,1000   ,200    ,200   ,100    ,1000   ,200   ,200   ,-14  ,EMERGENCIA",

            // estrategico saca em emergencia
            //"1000   ,ESTRATEGICO    ,100  ,1000   ,200    ,200   ,75     ,650    ,138   ,138   ,-14  ,EMERGENCIA",

    })

    public void testaEncomendaCombustivel(String qt,
                                          CentroDistribuicao.TIPOPOSTO tipoposto,
                                            int tAdt, int tGas, int tAlc1, int tAlc2,
                                            int tAdtEsp, int tGasEsp, int tAlc1Esp, int tAlc2Esp,
                                            int rEsp, CentroDistribuicao.SITUACAO situacao
                                          ) {

        CentroDistribuicao centro = new CentroDistribuicao(tAdt, tGas, tAlc1, tAlc2);

        int[] result = centro.encomendaCombustivel(Integer.parseInt(qt), tipoposto);

        Assertions.assertAll(
                () -> Assertions.assertEquals(tAdtEsp, result[1]),
                () -> Assertions.assertEquals(tGasEsp, result[2]),
                () -> Assertions.assertEquals(tAlc1Esp, result[3]),
                () -> Assertions.assertEquals(tAlc2Esp, result[4]),
                () -> Assertions.assertEquals(rEsp, result[0]),
                () -> Assertions.assertEquals(situacao, centro.getSituacao())
        );
    }

    @ParameterizedTest
    @CsvSource({
            "0      ,0      ,0      ,EMERGENCIA",
            "125    ,2500   ,625    ,EMERGENCIA",
            "126    ,2501   ,626    ,SOBRAVISO",
            "249    ,4999   ,1249   ,SOBRAVISO",
            "250    ,5000   ,1250   ,NORMAL",
            "500    ,10000  ,2500   ,NORMAL",
    })
    public void testaAbastecePostoValorLimite(int qtAditivo, int qtGasolina, int qtAlcool, CentroDistribuicao.SITUACAO situacaoEsp) {
        CentroDistribuicao centroVazio=
                new CentroDistribuicao(0,0,0,0);

        centroVazio.recebeAditivo(qtAditivo);
        centroVazio.recebeGasolina(qtGasolina);
        centroVazio.recebeAlcool(qtAlcool);

        Assertions.assertEquals(situacaoEsp, centroVazio.getSituacao());
    }

    @ParameterizedTest
    @CsvSource({
            "0      ,0      ,0      ,0",
            "500    ,10000  ,1250   ,1250",
    })
    public void testaCriaCentro(int qtAditivo, int qtGasolina, int qtAlcool1, int qtAlcool2) {
        Assertions.assertAll(
                () -> Assertions.assertDoesNotThrow(() -> new CentroDistribuicao(qtAditivo, qtGasolina, qtAlcool1, qtAlcool2)),
                () -> Assertions.assertEquals(qtAditivo, new CentroDistribuicao(qtAditivo, qtGasolina, qtAlcool1, qtAlcool2).gettAditivo()),
                () -> Assertions.assertEquals(qtGasolina, new CentroDistribuicao(qtAditivo, qtGasolina, qtAlcool1, qtAlcool2).gettGasolina()),
                () -> Assertions.assertEquals(qtAlcool1, new CentroDistribuicao(qtAditivo, qtGasolina, qtAlcool1, qtAlcool2).gettAlcool1()),
                () -> Assertions.assertEquals(qtAlcool2, new CentroDistribuicao(qtAditivo, qtGasolina, qtAlcool1, qtAlcool2).gettAlcool2())
                );
    }

    @ParameterizedTest
    @CsvSource({
            "0      ,0      ,0      ,-1",
            "0      ,0      ,-1     ,0",
            "0      ,-1     ,0      ,0",
            "-1     ,0      ,0      ,0",
            "500    ,10000  ,498    ,500",
            "500    ,10010  ,500    ,498",
    })
    public void testaCriaCentroException(int qtAditivo, int qtGasolina, int qtAlcool1, int qtAlcool2) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CentroDistribuicao(qtAditivo, qtGasolina, qtAlcool1, qtAlcool2));
    }


}
