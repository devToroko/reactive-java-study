package coding.toast.sec04;

import coding.toast.common.Util;
import coding.toast.sec04.helper.NameGenerator;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

@Slf4j
public class Lec03FluxSinkThreadSafety {
	public static void main(String[] args) throws InterruptedException {
		demo2();
	}
	
	private static void demo1() throws InterruptedException {
		var list = new ArrayList<Integer>();
		CountDownLatch latch = new CountDownLatch(11);
		Runnable runnable = () -> {
			for (int i = 0; i < 1000; i++) {
				list.add(i);
			}
			latch.countDown();
		};
		for (int i = 0; i < 10; i++) {
			Thread.ofPlatform().start(runnable);
		}
		latch.countDown();
		latch.await();
		log.info("1. list size: {}", list.size());
	}
	
	// flux sink is thread safe. we get all the 10000 items safely into array list
	private static void demo2() {
		var list = new ArrayList<String>();
		var generator = new NameGenerator();
		var flux = Flux.create(generator);
		flux.subscribe(list::add);
		
		Runnable runnable = () -> {
			for (int i = 0; i < 1000; i++) {
				generator.generate();
			}
		};
		
		for (int i = 0; i < 10; i++) {
			Thread.ofPlatform().start(runnable);
		}
		Util.sleepSeconds(3);
		log.info("2. list size: {}", list.size());
	}
}
