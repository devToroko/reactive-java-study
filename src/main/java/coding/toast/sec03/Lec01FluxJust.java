package coding.toast.sec03;

import coding.toast.common.Util;
import reactor.core.publisher.Flux;

public class Lec01FluxJust {

    public static void main(String[] args) {
        Flux.just(1, 2, 3, "sam")
                .subscribe(Util.subscriber());
    }
}