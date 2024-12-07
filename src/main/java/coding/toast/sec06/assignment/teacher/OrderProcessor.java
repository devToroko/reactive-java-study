package coding.toast.sec06.assignment.teacher;

import reactor.core.publisher.Flux;

public interface OrderProcessor {
	void consume(Order order);
	Flux<String> stream();
}
