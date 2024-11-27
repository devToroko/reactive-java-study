package coding.toast.sec02;

import coding.toast.common.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
public class Lec10MonoDefer {
    public static void main(String[] args) {
//        createPublisher(); //even do there is no subscriber, it takes time;;
//                .subscribe(Util.subscriber());

        // often... A time-consuming publisher create operation happens
        // at that moment, you can use Mono.defer method
        Mono.defer(Lec10MonoDefer::createPublisher)
                .subscribe(Util.subscriber());

    }

    private static Mono<Integer> createPublisher() {
        log.info("creating publisher");
        var list = List.of(1, 2, 3);
        Util.sleepSeconds(3); // publisher create operation is taking time
        return Mono.fromSupplier(() -> sum(list));
    }

    private static int sum(List<Integer> list) {
        log.info("finding the sum of {}", list);
        return list.stream().mapToInt(a->a).sum();
    }
}
