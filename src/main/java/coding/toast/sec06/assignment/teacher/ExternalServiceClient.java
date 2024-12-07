package coding.toast.sec06.assignment.teacher;

import coding.toast.common.AbstractHttpClient;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.Objects;

@Slf4j
public class ExternalServiceClient extends AbstractHttpClient {
	
	private Flux<Order> orderFlux;
	
	public Flux<Order> orderStream() {
		if (Objects.isNull(orderFlux)) {
			this.orderFlux = getOrderStream();
		}
		return this.orderFlux;
	}
	
	private Flux<Order> getOrderStream() {
		return this.httpClient.get()
			.uri("/demo04/orders/stream")
			.responseContent()
			.asString()
			.map(this::parse)
			.doOnNext(o -> log.info("{}", o))
			.publish()
			.refCount(2)
		;
	}
	
	private Order parse(String message) {
		var arr = message.split(":", -1);
		return new Order(
			arr[1],
			Integer.parseInt(arr[2]),
			Integer.parseInt(arr[3])
		);
	}
}
