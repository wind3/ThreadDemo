package com.thread.demo.domain;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thread.demo.factory.ProductFactory;

public class CommonResource {
    private static final Logger LOG = LoggerFactory.getLogger(CommonResource.class);
    private static int  total = 10;
    List< Product> prodList = new LinkedList< Product>();
    ProductFactory factory ;
    
    public CommonResource( ) {
        
    }
    
    public CommonResource( ProductFactory factory) {
        this.factory = factory;
    }
    /**
     * 生产
     */
    public void add() {    
           while(true) {
               synchronized(prodList) {
                   if (prodList.size() >= total) {
                       try {
                           LOG.info(Thread.currentThread().getName() + "is add wait");
                           prodList.wait();
                       } catch (InterruptedException e) {
                           // TODO Auto-generated catch block
                           e.printStackTrace();
                       }
                   }      
                   prodList.add(factory.newInstance());
                   prodList.notify();      
               }
              
           try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
           }
        
    }
    
    /**
     * 消费
     */
    public void remove() {           
        while(true) {
            synchronized(prodList) {
                if (prodList.size() == 0) {
                    try {
                        LOG.info(Thread.currentThread().getName() + "is remove wait");
                        prodList.wait();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                Product prod =  prodList.remove(prodList.size()-1);
                LOG.info(Thread.currentThread().getName()  +"消费" + prod.getName());
                prodList.notify();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } 
            
        }
    }
    

}
