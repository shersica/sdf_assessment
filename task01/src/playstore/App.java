package playstore;

public class App {

    private String name;
    private String category;
    private String rating;
    
    public App(String name, String category, String rating){
        this.name = name;
        this.category = category;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void parseFloat(String rating){
        Float.parseFloat(this.rating);
    }

    
}
