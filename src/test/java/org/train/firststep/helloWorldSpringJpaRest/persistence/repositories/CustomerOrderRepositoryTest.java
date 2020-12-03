package org.train.firststep.helloWorldSpringJpaRest.persistence.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.train.firststep.helloWorldSpringJpaRest.persistence.entities.CustomerOrder;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@ContextConfiguration(classes = CustomerOrderRepository.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class CustomerOrderRepositoryTest extends BaseJpaRepositoryTest {

    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @BeforeEach
    public void setUp() {
        transactionTemplate.setPropagationBehavior(Propagation.REQUIRES_NEW.value());
        transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_SERIALIZABLE);

        transactionTemplate.executeWithoutResult(transactionStatus -> {
            CustomerOrder customerOrder =
                    new CustomerOrder("C1", "Provide", "IPAD");

            customerOrderRepository.createOrUpdate(customerOrder);
        });
    }

    @Test
    public void shouldRetrieveAllOrders() {
        List<CustomerOrder> customerOrders = transactionTemplate.execute(transactionStatus -> customerOrderRepository.getAllOrders());

        assertThat(customerOrders, is(notNullValue()));
        assertThat(customerOrders.size(), is(1));

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                customerOrderRepository.createOrUpdate(new CustomerOrder("C2", "Modify", "iPhone"));
            }
        });

        customerOrders = transactionTemplate.execute(transactionStatus -> customerOrderRepository.getAllOrders());

        assertThat(customerOrders, is(notNullValue()));
        assertThat(customerOrders.size(), is(2));

        CustomerOrder c2 = transactionTemplate.execute(transactionStatus -> customerOrderRepository.getCustomerOrder("C2"));

        assertThat(c2, is(notNullValue()));
        assertThat(c2.getProductCode(), is("iPhone"));
    }

}