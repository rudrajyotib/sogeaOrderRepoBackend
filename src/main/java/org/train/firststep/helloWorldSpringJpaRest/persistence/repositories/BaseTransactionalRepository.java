package org.train.firststep.helloWorldSpringJpaRest.persistence.repositories;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class BaseTransactionalRepository {

    @Autowired
    @PersistenceContext
    protected EntityManager entityManager;

}
