public class Driver {
    public static void main(String[] args) {
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
}
