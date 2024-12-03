package coding.toast.sec05;

import coding.toast.common.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class Lec08SwitchIfEmpty {
	
	public static void main(String[] args) {
		// real-life example:
		// first call on redis...
		// if no cache exists then call database
		Flux.range(1, 10)
			.filter(i -> i > 10)
			.switchIfEmpty(fallback())
			.subscribe(Util.subscriber());
	}
	
	private static Flux<Integer> fallback() {
		return Flux.range(100, 3);
	}

}
