package coding.toast.sec04;

import coding.toast.common.Util;
import reactor.core.publisher.Flux;

public class Lec08GenerateWithState {
	public static void main(String[] args) {
		Flux.generate(() -> 0,
			(counter, sink) -> {
				var country = Util.faker().country().name();
				sink.next(country);
				counter++;
				if (counter == 10 || country.equalsIgnoreCase("canada")) {
					sink.complete();
				}
				return counter;
			}/*, counter -> {
			
			}*/)
			.subscribe(Util.subscriber());
	}
}
