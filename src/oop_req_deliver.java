import java.util.Date;

public class oop_req_deliver {
    String name, manufacturer;
    int quantity, cost;
    Date date;

    public oop_req_deliver(String name, String manufacturer, int quantity, int cost, Date date) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.quantity = quantity;
        this.cost = cost;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getCost() {
        return cost;
    }

    public Date getDate() {
        return date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
