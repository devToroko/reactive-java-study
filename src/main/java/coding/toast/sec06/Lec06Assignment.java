package coding.toast.sec06;

import coding.toast.common.Util;
import coding.toast.sec06.assignment.ExternalServiceClient;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class Lec06Assignment {
	
	private record Order(String item, String category, int price, int quantity){}
	
	private static AtomicInteger totalQuantity = new AtomicInteger(500);
	private static ConcurrentMap<String, Integer> revenueDb = new ConcurrentHashMap<>();
	private static ConcurrentMap<String, Integer> quantityDb = new ConcurrentHashMap<>();
	
	public static void main(String[] args) {
		var client = new ExternalServiceClient();
		Flux<String> orderFlux =
			client
				.getOrders()
				.publish().autoConnect(1);
		
		// revenue
		// orderFlux.subscribe(Util.subscriber("revenue ==> "));
		/*orderFlux.
			map(orderCsv -> {
				String[] split = orderCsv.split(":", -1);
				return new Order(split[0], split[1], Integer.parseInt(split[2]), Integer.parseInt(split[3]));
			})
			.doOnNext(order -> {
				Integer totalRevenue
					= revenueDb.getOrDefault(order.category(), 0);
				revenueDb.put(order.category(), totalRevenue + order.price());
			})
			.delayElements(Duration.ofSeconds(2))
			.doOnNext(order -> {
				log.info("total Revenue Via Category : ");
				for (var entry : revenueDb.entrySet()) {
					log.info("category : {} => revenue : {}", entry.getKey(), entry.getValue());
				}
			})
			.subscribe(Util.subscriber())*/
		;
		
		// inventory
		orderFlux.
			map(orderCsv -> {
				String[] split = orderCsv.split(":", -1);
				return new Order(split[0], split[1], Integer.parseInt(split[2]), Integer.parseInt(split[3]));
			})
			.doOnNext(order -> {
				if (!quantityDb.containsKey(order.category())) {
					quantityDb.put(order.category(), 500 - order.quantity());
				} else {
					Integer remainQuantity = quantityDb.getOrDefault(order.category(), 0);
					quantityDb.put(order.category(), remainQuantity - order.quantity());
				}
			})
			.delayElements(Duration.ofSeconds(2))
			.doOnNext(order -> {
				log.info("total Revenue Via Category : ");
				for (var entry : quantityDb.entrySet()) {
					log.info("category : {} => quantity : {}", entry.getKey(), entry.getValue());
				}
			})
			.subscribe(Util.subscriber());
		
		Util.sleepSeconds(20);
	}
}
