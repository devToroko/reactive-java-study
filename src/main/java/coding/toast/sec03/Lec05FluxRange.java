package coding.toast.sec03;

import coding.toast.common.Util;
import reactor.core.publisher.Flux;

public class Lec05FluxRange {
    public static void main(String[] args) {
        Flux.range(1, 10)
                .map(i -> Util.faker().funnyName().name())
                .subscribe(Util.subscriber());
    }
}
