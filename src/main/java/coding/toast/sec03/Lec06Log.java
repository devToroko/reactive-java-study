package coding.toast.sec03;

import coding.toast.common.Util;
import reactor.core.publisher.Flux;

public class Lec06Log {
    public static void main(String[] args) {
        Flux.range(1, 5)
                .log("range-to-map") // the request(N)'s N if from downstream request
                .map(i -> Util.faker().name().firstName())
                .log("map-to-subscribe")
                .subscribe(Util.subscriber());
    }
}
