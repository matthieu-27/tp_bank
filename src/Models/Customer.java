package Models;

/**
 * Classe représentant un client de la banque
 */
public class Customer {
    public int id;
    public String name;
    public String address;


    public Customer(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Customer(String name, String address){
        this.id = 0;
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer [" + id + "]: " + name + " - " + address;
    }
}