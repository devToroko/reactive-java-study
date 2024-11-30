package coding.toast.sec04;

import coding.toast.common.Util;
import reactor.core.publisher.Flux;

import java.util.stream.IntStream;

public class Lec05TakeOperator {
	public static void main(String[] args) {
		
	/*	Flux.range(1, 10)
			.log("take")
			.take(3)
			.log("sub")
			.subscribe(Util.subscriber());
	*/
		// takeWhile();
		takeUntil();
	}
	
	
	private static void take() {
		Flux.range(1, 10)
			.log("take")
			.take(3)
			.log("sub")
			.subscribe(Util.subscriber());
	}
	
	private static void takeWhile() {
		Flux.range(1, 10)
			.log("take")
			.takeWhile(i -> i < 5) // stop when the condition is not met
			.log("sub")
			.subscribe(Util.subscriber());
	}
	
	
	private static void takeUntil() {
		Flux.range(1, 10)
			.log("take")
			.takeUntil(i -> i > 5) // stop when the condition is met
											// It also include the item which is not condition met
			.log("sub")
			.subscribe(Util.subscriber());
	}
}
