package product;

public abstract class Product {
    protected String productCode;
    private String title;
    private double price;
    private int rate, ratersNum;
    protected String label;

    public Product(String title, double price){
        this.title = title;
        this.price = price;
        this.rate = 1;
        this.productCode = "#";
    }
    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){ // if the user has access as the admin
        this.title = title;
    }
    public double getPrice(){
        return this.price;
    }
    public void setPrice(float price){ // if the user has access as the admin
        this.price = price;
    }
    public int getRate(){
        return this.getRate();
    }
    public void updateRate(int rate){ // users can rate each product
        this.rate = (this.rate * ratersNum) + rate / ++ratersNum;
    }

}
