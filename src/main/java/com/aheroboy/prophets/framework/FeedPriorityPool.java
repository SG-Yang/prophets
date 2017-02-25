package com.aheroboy.prophets.framework;

import java.util.concurrent.PriorityBlockingQueue;

import org.springframework.scheduling.annotation.Scheduled;

public class FeedPriorityPool implements FeedPool {
	private static final PriorityBlockingQueue<Actor> QUEUE = new PriorityBlockingQueue<Actor>();

//	@Scheduled(cron = "*/2 * * * * *")
//	public void executeFileDownLoadTask() {
//		Thread current = Thread.currentThread();
//		System.out.println("定时任务1:" + current.getId());
//	}

}
