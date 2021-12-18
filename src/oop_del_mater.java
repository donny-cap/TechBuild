import java.util.Date;

public class oop_del_mater {
    int number, quantity, weight;
    String name, manufacturer;
    Date date;

    public oop_del_mater(int number, String name, String manufacturer, int quantity, int weight, Date date) {
        this.number = number;
        this.name = name;
        this.manufacturer = manufacturer;
        this.quantity = quantity;
        this.weight = weight;
        this.date = date;
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

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public Date getDate() {
        return date;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
