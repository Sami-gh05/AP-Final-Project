package product;

import shop.Data;

public class Phone extends Product{
    private String companyName;
    private String model;
    private String color;
    public Phone(String title, double price, String companyName, String model, String color){
        super(title, price);
        this.companyName = companyName;
        this.color = color;
        this.model = model;
        super.productCode += "PH" + String.valueOf(Data.getProducts().size() + 1); //because it is Phone
        super.label = "Phone";
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
