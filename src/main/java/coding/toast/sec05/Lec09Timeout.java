package coding.toast.sec05;

import coding.toast.common.Util;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * we can have multiple timeouts. the closest one to the subscriber will take effect for the subscriber.
 */
public class Lec09Timeout {
	public static void main(String[] args) {
		var mono = getProductName()
					.timeout(Duration.ofSeconds(1), fallback());
			
			mono
				.timeout(Duration.ofMillis(200)) //you can have many timeouts. but will effect only when reduce time than upstream timeout.
				.subscribe(Util.subscriber());
		
		Util.sleepSeconds(5);
	}
	
	private static Mono<String> getProductName() {
		return Mono.fromSupplier(() -> "service-" + Util.faker().commerce().productName())
			.delayElement(Duration.ofMillis(1900));
	}
	
	private static Mono<String> fallback() {
		return Mono.fromSupplier(() -> "fallback-" + Util.faker().commerce().productName())
			.delayElement(Duration.ofMillis(300))
			.doFirst(() -> System.out.println("do first")); // occurs only when subscriber subscribes
	}
}
