package coding.toast.sec06;

import coding.toast.common.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class Lec01ColdPublisher {
    public static void main(String[] args) {
        // 외부에 저장된 상태에 의해서 멀티 subscriber 에 대한 독립적인 동작이 안되는 경우가 발생하니 조심하자.
//        AtomicInteger atomicInteger = new AtomicInteger(0);
        var flux = Flux.create(fluxSink -> {
            log.info("invoked");
            for (int i = 0; i < 3; i++) {
//                fluxSink.next(atomicInteger.incrementAndGet());
                fluxSink.next(i);
            }
            fluxSink.complete();
        });

        flux.subscribe(Util.subscriber("sub1"));
        flux.subscribe(Util.subscriber("sub2"));
    }
}
