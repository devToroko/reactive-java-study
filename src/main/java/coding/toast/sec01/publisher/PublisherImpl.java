package coding.toast.sec01.publisher;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

@Slf4j
public class PublisherImpl implements Publisher<String> {
	
	@Override
	public void subscribe(Subscriber<? super String> subscriber) {
		var subscription = new SubscriptionImpl(subscriber);
		subscriber.onSubscribe(subscription);
	}
}
