package coding.toast.common;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultSubscriber<T> implements Subscriber<T> {
	
	private static final Logger log = LoggerFactory.getLogger(DefaultSubscriber.class);
	private final String name; // 서로 다른 subscriber 를 하나의 publisher 에 등록할 때 구분하기 위한 필드입니다.
	
	public DefaultSubscriber(String name) {
		this.name = name;
	}
	
	@Override
	public void onSubscribe(Subscription subscription) {
		subscription.request(Long.MAX_VALUE);
	}
	
	@Override
	public void onNext(T item) {
		log.info("{} received {}", this.name, item);
	}
	
	@Override
	public void onError(Throwable throwable) {
		log.error("{} error", this.name, throwable);
	}
	
	@Override
	public void onComplete() {
		log.info("{} received complete!", this.name);
	}
}
