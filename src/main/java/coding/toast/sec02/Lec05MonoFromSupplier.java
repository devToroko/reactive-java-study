package coding.toast.sec02;

import coding.toast.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec05MonoFromSupplier {
	private static final Logger log = LoggerFactory.getLogger(Lec05MonoFromSupplier.class);
	public static void main(String[] args) {
		
		var list = List.of(1, 2, 3);
		
		// not lazy!
		// Mono.just(sum(list));
			// .subscribe(Util.subscriber());
		
		// lazy!
		Mono.fromSupplier(() -> sum(list))
			.subscribe(Util.subscriber());
	}
	
	private static int sum(List<Integer> list) {
		log.info("finding them sum of {}", list);
		return list.stream().mapToInt(a -> a).sum();
	}
}
