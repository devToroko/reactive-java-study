package coding.toast.sec05;

import coding.toast.common.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class Lec06ErrorHandling {
	public static void main(String[] args) {
		/*// Mono.error(new RuntimeException("oops"))
		Mono.just(1)
			.onErrorComplete() // I Don't Want ERROR! Give Me Value Or Complete only!
			.subscribe(Util.subscriber())
		;*/
		
		// what if you want to skip the error and proceed further
		Flux.range(1, 10)
			.map(i -> i == 5 ? 5 / 0 : i)
			.onErrorContinue((ex, obj) -> log.error("==> {}", obj, ex))
			.subscribe(Util.subscriber());
		
	}
	
	
	// helpful for hardcoded value return
	private static void onErrorReturn() {
		// Mono.just(5) // also works on mono
		Flux.range(1, 10)
			// .onErrorReturn(-1) // not gonna work
			.map(i -> i == 5 ? 5 / 0 : i) // intentional
			// .onErrorReturn(-1) // will work
			
			.onErrorReturn(IllegalArgumentException.class, -1) // only handle when Exception is IllegalArgumentException
			.onErrorReturn(ArithmeticException.class, -2)
			.onErrorReturn(-3) // no matching Exception? then return -3
			.subscribe(Util.subscriber());
	}
	
	private static void onErrorResume() {
		Mono.error(new RuntimeException("oops"))
			.onErrorResume(ArithmeticException.class, ex -> fallback1()) // decide return value on runtime!
			.onErrorResume(ex -> fallback2()) // catch which is not ArithmeticException exception
			.onErrorReturn(-5) // when second onErrorReturn itself cause an error, this onErrorReturn will be invoked
			.subscribe(Util.subscriber());
	}
	
	private static Mono<Integer> fallback1() {
		return Mono.fromSupplier(() -> Util.faker().random().nextInt(10, 100));
	}
	
	private static Mono<Integer> fallback2() {
		// return Mono.fromSupplier(() -> Util.faker().random().nextInt(100, 1000));
		return Mono.error(new IllegalArgumentException());
	}
}
