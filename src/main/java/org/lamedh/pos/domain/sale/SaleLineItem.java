package org.lamedh.pos.domain.sale;

import org.lamedh.common.domain.EntityBase;
import org.lamedh.pos.domain.product.Product;

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
        if (quantity > 0)
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

    SaleLineItem() { }

    public BigDecimal getSubtotal() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }
}
