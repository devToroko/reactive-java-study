package coding.toast.sec03.assignment;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@Slf4j
@SuppressWarnings("ReactiveStreamsSubscriberImplementation")
public class StockSubscriber implements Subscriber<Integer> {
	
	private int balance = 1000;
	private int quantities = 0;
	private Subscription subscription;
	
	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
		this.subscription.request(Integer.MAX_VALUE);
	}
	
	@Override
	public void onNext(Integer price) {
		log.info("stock price : {}", price);
		
		if (price > 118) {
			log.info("지금이니!");
			balance = balance + (price * quantities);
			this.subscription.cancel();
			log.info("total profit: {}", balance);
			return;
		}
		
		int check = balance - price;
		if (check < 0) {
			log.info("텅장 엔딩");
			throw new IllegalStateException("No More Balance Left!");
		}
		
		log.info("존버 간다.");
		balance = check;
		quantities++;
	}
	
	@Override
	public void onError(Throwable t) {
		log.error("error", t);
	}
	
	@Override
	public void onComplete() {
		log.info("stock flux completed");
	}
}
