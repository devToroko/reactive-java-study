package coding.toast.sec01;

import coding.toast.sec01.publisher.PublisherImpl;
import coding.toast.sec01.subscriber.SubscriberImpl;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Slf4j
public class Demo {
	public static void main(String[] args) throws InterruptedException {
		// demo1();
		// demo2();
		// demo3();
		demo4();
	}
	
	private static void demo1() {
		var publisher = new PublisherImpl();
		var subscriber = new SubscriberImpl();
		publisher.subscribe(subscriber);
	}
	
	
	private static void demo2() throws InterruptedException {
		var publisher = new PublisherImpl();
		var subscriber = new SubscriberImpl();
		publisher.subscribe(subscriber);
		subscriber.getSubscription().request(3);
		Thread.sleep(Duration.of(2L, ChronoUnit.SECONDS));
		subscriber.getSubscription().request(3);
		Thread.sleep(Duration.of(2L, ChronoUnit.SECONDS));
		subscriber.getSubscription().request(3);
		Thread.sleep(Duration.of(2L, ChronoUnit.SECONDS));
		subscriber.getSubscription().request(3);
		Thread.sleep(Duration.of(2L, ChronoUnit.SECONDS));
		subscriber.getSubscription().request(3);
	}
	
	
	private static void demo3() throws InterruptedException {
		var publisher = new PublisherImpl();
		var subscriber = new SubscriberImpl();
		publisher.subscribe(subscriber);
		subscriber.getSubscription().request(3);
		Thread.sleep(Duration.of(2L, ChronoUnit.SECONDS));
		subscriber.getSubscription().cancel();
		subscriber.getSubscription().request(3);
		Thread.sleep(Duration.of(2L, ChronoUnit.SECONDS));
	}
	
	
	private static void demo4() throws InterruptedException {
		var publisher = new PublisherImpl();
		var subscriber = new SubscriberImpl();
		publisher.subscribe(subscriber);
		subscriber.getSubscription().request(3);
		Thread.sleep(Duration.of(2L, ChronoUnit.SECONDS));
		subscriber.getSubscription().request(11);
		Thread.sleep(Duration.of(2L, ChronoUnit.SECONDS));
		subscriber.getSubscription().request(3);
	}
}
