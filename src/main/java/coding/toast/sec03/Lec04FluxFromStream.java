package coding.toast.sec03;

import coding.toast.common.Util;
import reactor.core.publisher.Flux;

import java.util.List;

public class Lec04FluxFromStream {

    public static void main(String[] args) {

        var list = List.of(1, 2, 3, 4);
        var stream = list.stream();

        var flux = Flux.fromStream(stream);
        flux.subscribe(Util.subscriber("sub1"));
        flux.subscribe(Util.subscriber("sub2"));
        /*
        java.lang.IllegalStateException: stream has already been operated upon or closed
            at java.base/java.util.stream.AbstractPipeline.spliterator(AbstractPipeline.java:346)
            at reactor.core.publisher.FluxStream.subscribe(FluxStream.java:57)
            at reactor.core.publisher.Flux.subscribe(Flux.java:8848)
            at coding.toast.sec03.Lec04FluxFromStream.main(Lec04FluxFromStream.java:17)
         */
        // this is how java stream works...
        // stream is Disposable!

        // how to solve? just create new stream when subscriber activate!
        var flux1 = Flux.fromStream(list::stream);
        flux1.subscribe(Util.subscriber());
    }
}
