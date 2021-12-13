public class oop_materials {

    int quantity, cost;
    String name, purpose, madeby, type;

    public oop_materials(String name, String purpose, String madeby, String type, int quantity, int cost) {
        this.quantity = quantity;
        this.cost = cost;
        this.name = name;
        this.purpose = purpose;
        this.madeby = madeby;
        this.type = type;
    }


    public int getQuantity() {
        return quantity;
    }

    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public String getPurpose() {
        return purpose;
    }

    public String getMadeby() {
        return madeby;
    }

    public String getType() {
        return type;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public void setMadeby(String madeby) {
        this.madeby = madeby;
    }

    public void setType(String type) {
        this.type = type;
    }

}
