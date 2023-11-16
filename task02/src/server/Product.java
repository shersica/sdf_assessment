package server;

public class Product {

    private String title;
    private int id;
    private double price;
    private double rating;
    
    public Product(String title, int id, double price, double rating){
        this.title = title;
        this.id = id;
        this.price = price;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }


}
