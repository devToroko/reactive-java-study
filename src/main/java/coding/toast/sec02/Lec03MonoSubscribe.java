package coding.toast.sec02;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class Lec03MonoSubscribe {
	
	public static void main(String[] args) {
		var mono = Mono.just(1)
			.map(i -> i / 0);
		
		mono.subscribe(
			i -> log.info("received {}", i),
			error -> log.error("error", error),
			() -> {log.info("done!");},
			subscription -> subscription.request(1)
		);
		
	}
	
}
