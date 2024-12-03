package coding.toast.sec05;

import coding.toast.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec07DefaultIfEmpty {
	public static void main(String[] args) {
		// Optional.empty()
		// 	.orElseGet()
		/*
		Mono.just("vins")
			.log("empty")
			.defaultIfEmpty("fallback")
			.log("defaultIfEmpty")
			.subscribe(Util.subscriber());
		*/
		Flux.range(1, 10)
			.filter(i -> i > 11)
			.defaultIfEmpty(50)
			.subscribe(Util.subscriber())
		;
	}
}
