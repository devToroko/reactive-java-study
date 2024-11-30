package coding.toast.sec04;

import coding.toast.common.Util;
import coding.toast.sec01.subscriber.SubscriberImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec04FluxCreateDownstreamDemand {
	private static final Logger log = LoggerFactory.getLogger(Lec04FluxCreateDownstreamDemand.class);
	
	public static void main(String[] args) {
		produceOnDemand();
	}
	
	// if you don't like the default process then use under code.
	private static void produceOnDemand() {
		SubscriberImpl subscriber = new SubscriberImpl();
		Flux.<String>create(fluxSink -> {
			
			fluxSink.onRequest(request -> {
				for (int i = 0; i < request && !fluxSink.isCancelled(); i++) {
					String name = Util.faker().name().firstName();
					log.info("generated: {}", name);
					fluxSink.next(name);
				}
			});
			
		}).subscribe(subscriber);
		
		subscriber.getSubscription().request(5);
		subscriber.getSubscription().request(4);
		subscriber.getSubscription().request(1);
	}
	
	// default Flux
	private static void produceEarly() {
		SubscriberImpl subscriber = new SubscriberImpl();
		Flux.<String>create(fluxSink -> {
			for (int i = 0; i < 10; i++) {
				String name = Util.faker().name().firstName();
				log.info("2. generated: {}", name);
				fluxSink.next(name);
			}
			fluxSink.complete();
		}).subscribe(subscriber);
		// i didn't send signal 'request' but flux just flush it
		// it is not a bug! it is by design!
		
		// Flux creates everything (not lazy!) and store in Queue!
		// Then whenever subscriber requests for item, Flux will push it.
		
		Util.sleepSeconds(2);
		subscriber.getSubscription().request(2);
		Util.sleepSeconds(2);
		subscriber.getSubscription().request(2);
		Util.sleepSeconds(2);
		subscriber.getSubscription().cancel();
		Util.sleepSeconds(2);
		subscriber.getSubscription().request(2); // won't work!
		
		// by the way the queue size is unbound! be-careful for OOM
		// at the moment we will need BACK-PRESSURE
	}
}
