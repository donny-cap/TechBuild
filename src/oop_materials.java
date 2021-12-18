public class oop_materials {

    int number, quantity, weight, cost;
    String name, manufacturer, type;

    public oop_materials(int number, String name, String manufacturer, String type, int quantity, int weight, int cost) {
        this.number = number;
        this.name = name;
        this.manufacturer = manufacturer;
        this.type = type;
        this.quantity = quantity;
        this.weight = weight;
        this.cost = cost;
    }

    public int getNumber() {
        return number;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getWeight() {
        return weight;
    }

    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getType() {
        return type;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setType(String type) {
        this.type = type;
    }
}
