package org.train.firststep.helloWorldSpringJpaRest.web.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.train.firststep.helloWorldSpringJpaRest.persistence.entities.CustomerOrder;
import org.train.firststep.helloWorldSpringJpaRest.persistence.repositories.CustomerOrderRepository;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    @GetMapping("/find/{orderId}")
    public String findOrder(final @PathVariable String orderId)
    {
        CustomerOrder customerOrder = transactionTemplate.execute(transactionStatus -> customerOrderRepository.getCustomerOrder(orderId));
        if (customerOrder ==null)
        {
            return "Order not found";
        }
        return "OrderFound";
    }

}
