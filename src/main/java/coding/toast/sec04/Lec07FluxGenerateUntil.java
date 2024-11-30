package coding.toast.sec04;

import coding.toast.common.Util;
import reactor.core.publisher.Flux;

public class Lec07FluxGenerateUntil {
	public static void main(String[] args) {
		Flux.<String>generate((synchronousSink) -> {
				String country = Util.faker().country().name();
				synchronousSink.next(country);
			})
			.takeUntil("canada"::equalsIgnoreCase)
			.subscribe(Util.subscriber());
	}
	
	private static void demo1() {
		Flux.<String>generate(synchronousSink -> {
			var country = Util.faker().country().name();
			synchronousSink.next(country);
			if (country.equalsIgnoreCase("canada")) {
				synchronousSink.complete();
			}
		}).subscribe(Util.subscriber());
	}
	
	private static void demo2() {
		Flux.<String>generate(synchronousSink -> {
			var country = Util.faker().country().name();
			synchronousSink.next(country);
		})
		.takeUntil("canada"::equalsIgnoreCase)
		.subscribe(Util.subscriber());
	}
}
