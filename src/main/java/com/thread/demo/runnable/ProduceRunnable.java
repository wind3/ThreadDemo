package com.thread.demo.runnable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thread.demo.domain.CommonResource;
import com.thread.demo.service.ThreadService;

public class ProduceRunnable implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(ThreadService.class);
    
    CommonResource resource;
    
    public ProduceRunnable( ) {

    }
    
    public ProduceRunnable( CommonResource resource) {
        this.resource = resource;
    }
    @Override
    public void run() {
        resource.add();
    }

}
