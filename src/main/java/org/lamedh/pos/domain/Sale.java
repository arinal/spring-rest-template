package org.lamedh.pos.domain;

import org.lamedh.pos.common.domain.EntityBase;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Entity
public class Sale extends EntityBase {
    private String code;
    private LocalDateTime time;

    @ManyToOne
    private Employee cashier;

    @OneToMany
    private List<SaleLineItem> lineItems;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Employee getCashier() {
        return cashier;
    }

    public void setCashier(Employee cashier) {
        this.cashier = cashier;
    }

    public Collection<SaleLineItem> getLineItems() {
        return lineItems;
    }

    public Sale() {
        lineItems = new ArrayList<>();
        time = LocalDateTime.now();
    }

    public void addLineItem(Product product, int quantity) {
        SaleLineItem sli = getByProduct(product).orElseGet(() -> addLineItem(new SaleLineItem(product, 0)));
        sli.addQuantity(quantity);
    }

    public BigDecimal getTotal() {
        return lineItems.stream().map(sli -> sli.getSubTotal()).reduce(BigDecimal.ZERO,  (a, b) -> a.add(b));
    }

    public Optional<SaleLineItem> getByProduct(Product product) {
        return lineItems.stream().filter(sli -> sli.getProduct().equals(product)).findFirst();
    }

    private SaleLineItem addLineItem(SaleLineItem saleLineItem) {
        lineItems.add(saleLineItem);
        return saleLineItem;
    }
}