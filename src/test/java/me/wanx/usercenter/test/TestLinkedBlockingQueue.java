package me.wanx.usercenter.test;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class TestLinkedBlockingQueue {
	
	public static void main(String[] args) {
		ExecutorService exe = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS, 
													new LinkedBlockingQueue<Runnable>(5), 
													new ThreadPoolExecutor.CallerRunsPolicy() );
		for(int k = 0; k < 5; k++){
			exe.execute(new ExeThread(k));
		}
		System.out.println("for循环end");
		exe.shutdown();
		System.out.println(exe.isTerminated());
		System.out.println(exe.isShutdown());
		while(exe.isTerminated()){
			System.out.println("等待exe执行完毕");
		}
		TestThread tt = new TestThread();
		TestThread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println("e:"+e);
				System.out.println(t);
				t.run();
			}
		});
		tt.start();
	}

}

class ExeThread implements Runnable{
	private String id;
	public ExeThread(){}
	
	public ExeThread(int id){
		this.id = "Thread-"+id;
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("线程："+this.id+",执行完毕");
	}
	
}

class TestThread extends Thread{
	@Override
	public void run() {
		double i = 12/0;
	}
}
