public class oop_req_deliver {
    int quantity;
    String name;

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public oop_req_deliver(int quantity, String name) {
        this.quantity = quantity;
        this.name = name;
    }
}
