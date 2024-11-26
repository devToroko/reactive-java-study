package coding.toast.sec02;

import coding.toast.sec01.subscriber.SubscriberImpl;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class Lec03MonoSubscribe {
	
	public static void main(String[] args) {
		
		var mono = Mono.just("vins");
		var subscriber = new SubscriberImpl();
		mono.subscribe(subscriber);
		// subscriber.getSubscription().request(10);
		subscriber.getSubscription().cancel();
		subscriber.getSubscription().request(10);
		
	}
	
}
