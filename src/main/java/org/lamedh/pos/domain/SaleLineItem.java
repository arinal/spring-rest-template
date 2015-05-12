package org.lamedh.pos.domain;

import org.lamedh.pos.common.domain.EntityBase;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
public class SaleLineItem extends EntityBase {
    private int quantity;
    private BigDecimal unitPrice;

    @ManyToOne
    private Product product;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
        this.unitPrice = product.getUnitPrice();
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public SaleLineItem(Product product, int quantity) {
        setProduct(product);
        setQuantity(quantity);
    }

    public BigDecimal getSubTotal() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }
}
