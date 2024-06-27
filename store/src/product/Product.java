public abstract class Product {
    private String title;
    private float price;
    private short rate;
    public Product(String title, float price){
        this.title = title;
        this.price = price;
        this.rate = 0;
    }
    public abstract String getTitle();
    public abstract void setTitle();
    public abstract float getPrice();
    public abstract void setPrice();
    public abstract short getRate();
    public abstract void setRate();

}
