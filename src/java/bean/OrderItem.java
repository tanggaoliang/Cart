package bean;

public class OrderItem {
    private int id;
    private Product product;
    private int num;
    private Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public OrderItem(int id, Product product, int num) {
        this.id = id;
        this.product = product;
        this.num = num;
    }

    public OrderItem(Product product, int num) {
        this.product = product;
        this.num = num;
    }

    public OrderItem(int id, Product product, int num, Order order) {
        this.id = id;
        this.product = product;
        this.num = num;
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", product=" + product +
                ", num=" + num +
                ", order=" + order +
                '}';
    }
}
