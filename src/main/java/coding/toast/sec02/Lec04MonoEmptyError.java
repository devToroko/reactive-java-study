package coding.toast.sec02;

import reactor.core.publisher.Mono;

public class Lec04MonoEmptyError {
	public static void main(String[] args) {
		getUsername(3)
			.subscribe(System.out::println, System.err::println);
	}
	
	private static Mono<String> getUsername(int userId) {
		return switch (userId) {
			case 1 -> Mono.just("sam");
			case 2 -> Mono.empty();
			default -> Mono.error(new RuntimeException("invalid input"));
		};
	}
}
