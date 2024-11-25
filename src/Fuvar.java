import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Fuvar {
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private int id;
    private Date start;
    private int time;
    private float miles;
    private float price;
    private float tip;
    private String paytype;

    public Fuvar(String[] data) throws ParseException {
        this.id = Integer.parseInt(data[0]);
        this.start = formatter.parse(data[1]);
        this.time = Integer.parseInt(data[2]);
        this.miles = Float.parseFloat(data[3]);
        this.price = Float.parseFloat(data[4]);
        this.tip = Float.parseFloat(data[5]);
        this.paytype = data[6];
    }

    @Override
    public String toString() {
        return "Fuvar{" +
                "id=" + id +
                ", start=" + start +
                ", time=" + time +
                ", miles=" + miles +
                ", price=" + price +
                ", tip=" + tip +
                ", paytype='" + paytype + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public Date getStart() {
        return start;
    }

    public int getTime() {
        return time;
    }

    public float getMiles() {
        return miles;
    }

    public float getPrice() {
        return price;
    }

    public float getTip() {
        return tip;
    }

    public String getPaytype() {
        return paytype;
    }
}
