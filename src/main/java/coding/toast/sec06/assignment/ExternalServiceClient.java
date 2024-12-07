package coding.toast.sec06.assignment;

import coding.toast.common.AbstractHttpClient;
import reactor.core.publisher.Flux;

public class ExternalServiceClient extends AbstractHttpClient {
	public Flux<String> getOrders() {
		return this.httpClient.get()
			.uri("/demo04/orders/stream")
			.responseContent()
			.asString();
	}
}
