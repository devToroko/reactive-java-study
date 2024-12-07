package coding.toast.sec07;

import coding.toast.common.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class Lec03MultipleSubscribeOn {
	public static void main(String[] args) {
		var flux = createFlux()
			.doOnNext(v -> log.info("value: {}", v))
			.doFirst(() -> log.info("first1"))
			.subscribeOn(Schedulers.boundedElastic())
			.doFirst(() -> log.info("first2"));
		
		Runnable runnable1 = () -> flux.subscribe(Util.subscriber());
		
		Thread.ofPlatform().start(runnable1);
		
		Util.sleepSeconds(2);
		
		// 왜 필요함?
		
	}
	
	private static Flux<Integer> createFlux() {
		return Flux.<Integer>create(sink -> {
				for (int i = 1; i <= 3; i++) {
					log.info("generating: {}", i);
					sink.next(i);
				}
				sink.complete();
			})
			.subscribeOn(Schedulers.newParallel("codingToast")); // one More!
	}
}
