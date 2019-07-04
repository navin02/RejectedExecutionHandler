package com.ns.rejectedexecution;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Test {
	public static void main(String[] args) {
		System.out.println(Runtime.getRuntime().availableProcessors());
		ThreadPoolExecutor ex = (ThreadPoolExecutor) Executors
				.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		RejectedTaskHandler handler = new RejectedTaskHandler();
		ex.setRejectedExecutionHandler(handler);
		Task task = null;
		for (int i = 0; i < 5; i++) {
			task = new Task("Task-" + i);
			ex.execute(task);
		}
		ex.shutdown();
		ex.execute(new Task("rejected task"));
	}
}
