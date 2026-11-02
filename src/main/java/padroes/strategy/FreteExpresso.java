package padroes.strategy;

public class FreteExpresso implements CalculoFrete {

    @Override
    public double calcular(double pesoKg) {
        return 20.0 + 4.0 * pesoKg;
    }
}
