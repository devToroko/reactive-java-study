package coding.toast.common;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;

import java.time.Duration;

public class Util {
	
	private static final Faker faker = Faker.instance();
	
	public static <T> Subscriber<T> subscriber() {
		return subscriber("");
	}
	
	public static <T> Subscriber<T> subscriber(String name) {
		return new DefaultSubscriber<>(name);
	}
	
	public static Faker faker() {
		return faker;
	}
	
	public static void sleepSecond(int seconds) {
		try {
			Thread.sleep(Duration.ofSeconds(seconds));
		} catch (InterruptedException e) {
			
			Thread.currentThread().interrupt();
			throw new RuntimeException(e);
		}
	}
}
