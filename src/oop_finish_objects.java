public class oop_finish_objects {

    int quantity;
    String name, area;


    public oop_finish_objects(String name, String area, int quantity) {
        this.name = name;
        this.area = area;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public String getArea() {
        return area;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArea(String area) {
        this.area = area;
    }

}
