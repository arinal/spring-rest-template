package org.lamedh.pos.domain.product;

import org.lamedh.common.domain.EntityBase;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Product extends EntityBase {
    private String code;
    private String name;
    private BigDecimal unitPrice;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setUnitPriceByInt(int unitPriceInt) {
        setUnitPrice(BigDecimal.valueOf(unitPriceInt));
    }
}