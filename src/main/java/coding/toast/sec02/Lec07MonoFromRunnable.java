package coding.toast.sec02;

import coding.toast.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec06MonoFromCallable {
	private static final Logger log = LoggerFactory.getLogger(Lec06MonoFromCallable.class);
	public static void main(String[] args) {
		var list = List.of(1, 2, 3);
		// 1. Supplier 뿐만 아니라 Callable 인터페이스를 사용하고 싶은 경우
		// 2. callable 은 Exception 에 대한 고려가 되어 있다. 그래서 따로 try-catch 를 지정 안해도 된다.
		Mono.fromCallable(() -> sum(list))
			.subscribe(Util.subscriber());
	}
	
	private static int sum(List<Integer> list) {
		log.info("finding them sum of {}", list);
		return list.stream().mapToInt(a -> a).sum();
	}
}
