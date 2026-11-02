package padroes.strategy;

public class FreteEconomico implements CalculoFrete {

    @Override
    public double calcular(double pesoKg) {
        return 10.0 + 2.0 * pesoKg;
    }
}
