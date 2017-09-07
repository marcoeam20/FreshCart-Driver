package shopwise.freshcartdriverfinal.Orders;

/**
 * Created by macbook on 06/09/2017.
 */

public class Orders {

    public Orders() {
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(String prodPrice) {
        this.prodPrice = prodPrice;
    }

    private String prodName;
    private String prodPrice;



}
