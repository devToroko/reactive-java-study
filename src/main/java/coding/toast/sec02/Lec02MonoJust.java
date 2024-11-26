package coding.toast.sec02;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Stream;

@Slf4j
public class Lec01LazyStream {
	
	public static void main(String[] args) {
		List<Integer> list =
			Stream.of(1)
			.peek(i -> log.info("received : {}", i))
			.toList();
	
		
	}
}
