package coding.toast.sec03;

import coding.toast.common.Util;
import coding.toast.sec03.assignment.StockPriceObserver;
import coding.toast.sec03.client.ExternalServiceClient;

public class Lec12Assignment {
	public static void main(String[] args) {
		var client = new ExternalServiceClient();
		var subscriber = new StockPriceObserver();
		
		client.getPriceChanges()
			.subscribe(subscriber);
		
		Util.sleepSeconds(22);
	}
}
