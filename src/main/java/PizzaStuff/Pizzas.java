package PizzaStuff;

public class Pizzas {
    private final int pizzaNr;
    private final String pizzaName;
    private final String pizzaFilling;
    private final int pizzaPrice;


    public Pizzas(int pizzaNr, String pizzaName, String pizzaFilling, int pizzaPrice) {
        this.pizzaNr = pizzaNr;
        this.pizzaName = pizzaName;
        this.pizzaFilling = pizzaFilling;
        this.pizzaPrice = pizzaPrice;
    }


    public int getPizzaNr() {
        return pizzaNr;
    }
    public String getPizzaName() {
        return pizzaName;
    }
    public String getPizzaFilling() {
        return pizzaFilling;
    }
    public int getPizzaPrice() {
        return pizzaPrice;
    }
}
