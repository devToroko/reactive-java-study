package coding.toast.sec03;

import coding.toast.common.Util;
import reactor.core.publisher.Flux;

public class Lec02MultipleSubscribers {

    public static void main(String[] args) {
        var flux = Flux.just(1, 2, 3, 4, 5, 6, 7);
        flux.subscribe(Util.subscriber("sam1"));
        flux.subscribe(Util.subscriber("sam2"));
        flux
                .filter(i -> i % 2 == 0)
                .map(i -> i + "a")
                .subscribe(Util.subscriber("sam3"));
    }
}
