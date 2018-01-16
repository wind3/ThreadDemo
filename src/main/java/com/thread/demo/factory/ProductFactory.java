package com.thread.demo.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thread.demo.domain.Product;

public class ProductFactory {
    private static final Logger LOG = LoggerFactory.getLogger(ProductFactory.class);
    private String name = "产品";
    private static final int price = 100;
    private static int  count = 0; 
    public  Product newInstance() {

        LOG.info(Thread.currentThread().getName() +"生产" + name + count);
        return new Product(name + count++, price);
    }
}
