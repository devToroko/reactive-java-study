package coding.toast.sec03.assignment;

import coding.toast.common.Util;

public class Demo {
	public static void main(String[] args) {
		StockFlux.create()
			.subscribe(new StockSubscriber());
		
		Util.sleepSeconds(22);
	}
}
