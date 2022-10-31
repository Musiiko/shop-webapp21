package org.example.domain;

public class ProductInOrder {
    private Product product;
    private int count;

    public ProductInOrder(Product product, int count){
        this.setProduct(product);
        this.setCount(count);
    }
    public Product getProduct() {
        return product;
    }

    public int getCount() {
        return count;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return getProduct() + ", count=" + getCount();
    }

}
