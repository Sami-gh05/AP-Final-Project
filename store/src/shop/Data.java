import java.util.HashSet;
import java.util.Set;


public class Data {
    protected static Set<User> users;
    protected static Admin admin;
    protected Set<Product> products;
    public Data(){
        users = new HashSet<>();
        admin = new Admin();
        products = new HashSet<>();
    }
    public void updateUser(User user){
        //TODO
    }
    public  void updateProducts(Product product){
        //TODO
    }

}
