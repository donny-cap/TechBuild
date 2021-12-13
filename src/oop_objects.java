public class oop_objects {
    int quantity;
    String name,destination;

    public oop_objects(String name, int quantity, String destination) {
        this.name = name;
        this.quantity = quantity;
        this.destination = destination;
    }

    public String getName() {
        return name;
    }
    public int getQuantity() {
        return quantity;
    }
    public String getDestination() {
        return destination;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
