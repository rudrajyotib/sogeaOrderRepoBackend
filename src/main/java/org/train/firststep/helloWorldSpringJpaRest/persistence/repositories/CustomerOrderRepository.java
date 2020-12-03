package org.train.firststep.helloWorldSpringJpaRest.persistence.repositories;

import org.springframework.stereotype.Repository;
import org.train.firststep.helloWorldSpringJpaRest.persistence.entities.CustomerOrder;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CustomerOrderRepository extends BaseTransactionalRepository{


    public void createOrUpdate(CustomerOrder customerOrder)
    {
        entityManager.merge(customerOrder);
    }

    public CustomerOrder getCustomerOrder(String customerOrderId)
    {
        return entityManager.find(CustomerOrder.class, customerOrderId);
    }

    public List<CustomerOrder> getAllOrders()
    {
        TypedQuery<CustomerOrder> allOrders = entityManager.createNamedQuery("listAllOrders", CustomerOrder.class);
        return allOrders.getResultList();
    }
}
