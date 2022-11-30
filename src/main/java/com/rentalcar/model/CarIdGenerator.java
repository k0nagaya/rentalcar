package com.rentalcar.model;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Id generator for {@link Car} entity.
 */
public class CarIdGenerator implements IdentifierGenerator {

    private final static AtomicLong COUNTER = new AtomicLong();

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object obj) throws HibernateException {
        return "C" + COUNTER.incrementAndGet();
    }
}
