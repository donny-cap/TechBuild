import java.sql.Date;

public class oop_finish_objects {

    int number, square;
    String objects, company, address;
    Date date;

    public oop_finish_objects(int number, String objects, String company, String address, int square, Date date) {
        this.number = number;
        this.square = square;
        this.objects = objects;
        this.company = company;
        this.address = address;
        this.date = date;
    }

    public int getNumber() {
        return number;
    }

    public int getSquare() {
        return square;
    }

    public String getObjects() {
        return objects;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public Date getDate() {
        return date;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setSquare(int square) {
        this.square = square;
    }

    public void setObjects(String objects) {
        this.objects = objects;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
