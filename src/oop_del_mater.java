public class oop_del_mater {
    int quantity;
    String name, date;

    public oop_del_mater(String name, int quantity, String date) {
        this.name = name;
        this.quantity = quantity;
        this.date = date;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }
}
