package org.lamedh.pos.domain.sale;

import org.lamedh.common.domain.EntityBase;
import org.lamedh.pos.domain.employee.Employee;
import org.lamedh.pos.domain.product.Product;

import javax.persistence.CascadeType;
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

    @OneToMany(cascade = CascadeType.ALL)
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

    public Iterable<SaleLineItem> getLineItems() {
        return lineItems;
    }

    public Sale() {
        lineItems = new ArrayList<>();
        time = LocalDateTime.now();
    }

    public void addLineItem(SaleLineItem saleLineItem) {
        Optional<SaleLineItem> found = getByProduct(saleLineItem.getProduct());
        found.ifPresent(sli -> sli.addQuantity(saleLineItem.getQuantity()));
        found.orElseGet(() -> addToList(saleLineItem));
    }

    public void addLineItem(Product product, int quantity) {
        addLineItem(new SaleLineItem(product, quantity));
    }

    public BigDecimal getTotal() {
        return lineItems.stream().map(sli -> sli.getSubTotal()).reduce(BigDecimal.ZERO,  (a, b) -> a.add(b));
    }

    public Optional<SaleLineItem> getByProduct(Product product) {
        return lineItems.stream().filter(sli -> sli.getProduct().equals(product)).findFirst();
    }

    public void doGroupLineItems() {
        SaleLineItem[] cloned = new SaleLineItem[lineItems.size()];
        lineItems.toArray(cloned);
        lineItems.clear();
        for (SaleLineItem sli : cloned)
            addLineItem(sli);
    }

    private SaleLineItem addToList(SaleLineItem saleLineItem) {
        lineItems.add(saleLineItem);
        return saleLineItem;
    }
}