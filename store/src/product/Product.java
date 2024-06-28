public abstract class Product {
    protected String productCode;
    private String title;
    private float price;
    private int rate, ratersNum;

    public Product(String title, float price){
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
    public float getPrice(){
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
