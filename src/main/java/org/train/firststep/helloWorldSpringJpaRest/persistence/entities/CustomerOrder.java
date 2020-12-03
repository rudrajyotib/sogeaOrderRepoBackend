package org.train.firststep.helloWorldSpringJpaRest.persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "CUSTOMER_ORDER")
@NamedQuery(name = "listAllOrders", query = "select c from CustomerOrder c")
public class CustomerOrder {

    @Id
    @Column(name="CUSTOMER_ID")
    private String customerOrderId;

    @Column(name = "ORDER_TYPE")
    private String orderType;

    @Column(name = "PRODUCT_CODE")
    private String productCode;


    public CustomerOrder(String customerOrderId, String orderType, String productCode) {
        this.customerOrderId = customerOrderId;
        this.orderType = orderType;
        this.productCode = productCode;
    }

    public CustomerOrder() {
    }

    public String getCustomerOrderId() {
        return customerOrderId;
    }

    public String getOrderType() {
        return orderType;
    }

    public String getProductCode() {
        return productCode;
    }
}
