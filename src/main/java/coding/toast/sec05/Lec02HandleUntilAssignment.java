package coding.toast.sec05;

import coding.toast.common.Util;
import reactor.core.publisher.Flux;

public class Lec02HandleUntilAssignment {
    public static void main(String[] args) {
        demo2WithHandle();
    }

    private static void demo2WithHandle() {
        Flux.<String>generate(synchronousSink -> {
                    var country = Util.faker().country().name();
                    synchronousSink.next(country);
                })
                .handle((item, sink) -> {
                    if (item.equalsIgnoreCase("canada")) {
                        sink.next(item);
                        sink.complete();
                    } else {
                        sink.next(item);
                    }
                })
                .cast(String.class)
                .subscribe(Util.subscriber());
    }

    private static void demo2() {
        Flux.<String>generate(synchronousSink -> {
                    var country = Util.faker().country().name();
                    synchronousSink.next(country);
                })
                .takeUntil("canada"::equalsIgnoreCase)
                .subscribe(Util.subscriber());
    }
}
