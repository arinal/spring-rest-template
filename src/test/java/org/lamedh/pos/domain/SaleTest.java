package org.lamedh.pos.domain;

import org.junit.Before;
import org.junit.Test;
import org.lamedh.pos.domain.product.Product;
import org.lamedh.pos.domain.sale.Sale;
import org.lamedh.pos.domain.sale.SaleLineItem;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.lamedh.common.iterable.IterableCommon.stream;

public class SaleTest {

    @Test
    public void add_2_momogi_and_1_pepsi_should_totaled_6000() {
        Sale sale = new Sale();
        sale.addLineItem(momogi, 2);
        sale.addLineItem(pepsi, 1);
        assertThat(sale.getTotal().intValue()).isEqualTo(6000);
    }

    @Test
    public void add_1_ayam_and_1_pepsi_should_totaled_55000() {
        Sale sale = new Sale();
        sale.addLineItem(ayam, 1);
        sale.addLineItem(pepsi, 1);
        assertThat(sale.getTotal().intValue()).isEqualTo(55000);
    }

    @Test
    public void total_with_changed_product_price_should_remains_constant() {
        Sale sale = new Sale();
        sale.addLineItem(momogi, 1);

        assertThat(sale.getTotal().intValue()).isEqualTo(500);
        momogi.setUnitPriceByLong(300);
        assertThat(sale.getTotal().intValue()).isEqualTo(500);
    }

    @Test
    public void add_line_item_should_automatically_be_grouped_by_product() {
        Sale sale = new Sale();
        sale.addLineItem(momogi, 2);
        sale.addLineItem(pepsi, 1);

        assertThat(stream(sale.getLineItems()).count()).isEqualTo(2);
        sale.addLineItem(momogi, 1);
        assertThat(stream(sale.getLineItems()).count()).isEqualTo(2);
        assertThat(sale.getLineItems()).extracting("quantity").contains(3, 1);
    }

    @Test
    public void add_2_momogi_1_momogi_and_1_pepsi_when_group_is_performed_then_3_momogi_and_1_pepsi() {
        Sale sale = new Sale();

        List<SaleLineItem> lineItems = (List<SaleLineItem>) sale.getLineItems();
        lineItems.add(new SaleLineItem(momogi, 2));
        lineItems.add(new SaleLineItem(momogi, 1));
        lineItems.add(new SaleLineItem(pepsi, 1));
        assertThat(lineItems.size()).isEqualTo(3);

        sale.doGroupLineItems();
        assertThat(lineItems.size()).isEqualTo(2);
        assertThat(sale.getLineItems()).extracting("quantity").contains(3, 1);
    }

    @Before
    public void setup() {
        momogi = new Product();
        momogi.setId(1);
        momogi.setCode("P01");
        momogi.setName("Momogi");
        momogi.setUnitPriceByLong(500);

        pepsi = new Product();
        pepsi.setId(2);
        pepsi.setCode("P02");
        pepsi.setName("Pepsi");
        pepsi.setUnitPriceByLong(5000);

        ayam = new Product();
        ayam.setId(3);
        ayam.setCode("P03");
        ayam.setName("Ayam");
        ayam.setUnitPriceByLong(50000);
    }

    private Product momogi;
    private Product pepsi;
    private Product ayam;
}
