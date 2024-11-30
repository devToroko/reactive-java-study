package coding.toast.sec04;

import coding.toast.common.Util;
import reactor.core.publisher.Flux;

public class Lec01FluxCreate {
	public static void main(String[] args) {
		Flux.<String>create(fluxSink -> {
				String country;
				do {
					country = Util.faker().country().name();
					fluxSink.next(country);
				} while (!country.equalsIgnoreCase("canada"));
				fluxSink.complete();
			})
			.subscribe(Util.subscriber());
		
	}
}
