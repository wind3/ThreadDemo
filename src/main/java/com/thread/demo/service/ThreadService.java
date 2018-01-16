package com.thread.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thread.demo.domain.Product;

public class ThreadService {
    private static final Logger LOG = LoggerFactory.getLogger(ThreadService.class);
    private List<Product> prodList  = new ArrayList<Product>(6);
    Object produce = new Object();
    Object consume = new Object();
    /**
     * 生产者
     */
    public void producer(Object consume) {
        synchronized(produce) {
            int count = 0;
            String name = "产品:";
            while(true) {
                if (prodList.size() < 6) {
                    LOG.info("add product = " + count);
                    prodList.add(new Product(name + count, count ++));
                }
                try {
                    consume.notify();
                    produce.wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            
        }
    }
    
    /**
     * 消费者
     */
    public void consumer(Object produce) {           
        synchronized(consume) {
            int count = 0;
            while(true) {
                if (prodList.size() > 0) {
                    LOG.info("remove product =" + count);
                    prodList.remove(++count);
                }
                try {
                    produce.notify();
                    consume.wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            
        }
        
    }
}
