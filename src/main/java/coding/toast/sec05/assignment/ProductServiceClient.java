package coding.toast.sec05.assignment;

import coding.toast.common.AbstractHttpClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class ProductServiceClient extends AbstractHttpClient {
	// /demo03/timeout-fallback/product/{id}
	// /demo03/empty-fallback/product/{id}
	public Mono<String> getProductName(int productId) {
		return this.httpClient.get()
			.uri("/demo03/product/" + productId)
			.responseContent()
			.asString()
			.next()
			.timeout(Duration.ofSeconds(2), timeoutFallback(productId))
			.switchIfEmpty(emptySwitch(productId));
	}

	private Mono<String> timeoutFallback(int productId) {
		return this.httpClient.get()
				.uri("/demo03/timeout-fallback/product/" + productId)
				.responseContent().asString().next();
	}
	private Mono<String> emptySwitch(int productId) {
		return this.httpClient.get()
				.uri("/demo03/empty-fallback/product/" + productId)
				.responseContent().asString().next();
	}

}