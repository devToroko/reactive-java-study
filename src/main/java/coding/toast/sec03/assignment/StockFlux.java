package coding.toast.sec03.assignment;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;

public class StockFlux {
	public static Flux<Integer> create() {
		return Flux.range(1, 40)
			.delayElements(Duration.ofMillis(500))
			.map(i -> new Random().nextInt(80, 121));
	}
}
