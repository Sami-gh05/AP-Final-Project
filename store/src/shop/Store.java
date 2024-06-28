public class Store {
    private String storeName;
    private static float storeBalance = 0;

    public Store(String name,String adminID, String adminPass){ //for when the store is created for the first time
        storeName = name;
        new Data();
    }
    public static void balanceIncrease(float amount){
        storeBalance += amount;
    }

}
