package com.aheroboy.prophets.framework;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.Executor;

@Data
public class ActorSystem implements ActorService{

	@Autowired
	private AutoUpdateService aus;

	@Autowired
	private AutoDiscoveryService ads;
	@Autowired
	private Executor asyncExecutor;

	@Autowired
	private ActorManager am;
	
	@Autowired
	private SystemMonitorService sms;

	public void init() {
	    am.init();
		aus.init();
		ads.init();
		sms.init();
	}


	@Override
	public void start() {
		ads.start();
		aus.start();
		sms.start();
		am.start();
	}

}
