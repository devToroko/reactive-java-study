package coding.toast.sec04;

import coding.toast.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec06FluxGenerate {
	private static final Logger log = LoggerFactory.getLogger(Lec06FluxGenerate.class);
	
	public static void main(String[] args) {
		
		// you can imagine generate method itself use for loop inside,
		// and each loop it executes the lambda below.
		Flux.<Integer>generate(synchronousSink -> {
				// this lambda will run 4 loop
				log.info("invoked");
				synchronousSink.next(1);
				// synchronousSink.next(2);
				// synchronousSink.complete();
			})
			.take(4)
			.subscribe(Util.subscriber());
		
	}
}
