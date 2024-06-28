package product;

public class Phone extends Product{
    private String companyName;
    private String model;
    private String color;
    private int adder = 1;
    public Phone(String title, double price, String companyName, String model, String color){
        super(title, price);
        this.companyName = companyName;
        this.color = color;
        this.model = model;
        super.productCode += "PH" + String.valueOf(adder); //because it is Phone
        adder++;
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
