package product;

import shop.Data;

public class Cloth extends Product{
    private String size;
    private String color;
    private String sex;
    public Cloth(String title, double price, String size, String color, String sex){
        super(title, price);
        this.size = size;
        this.color = color;
        this.sex = sex;
        super.productCode += "C" + String.valueOf(Data.getProducts().size() + 1); //because it is cloth
        super.label = "Cloth";
    }

    public String getSex() {
        return sex;
    }

    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
