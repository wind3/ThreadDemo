package com.thread.demo.runnable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thread.demo.domain.CommonResource;
import com.thread.demo.service.ThreadService;

public class ConsumeRunnable implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(ThreadService.class);
    CommonResource resource;
    
    public ConsumeRunnable( ) {

    }
    
    public ConsumeRunnable( CommonResource resource) {
        this.resource = resource;
    }
    
    @Override
    public void run() {
        resource.remove();
    }

}
