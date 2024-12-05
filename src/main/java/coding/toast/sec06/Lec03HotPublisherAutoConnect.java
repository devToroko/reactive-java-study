package coding.toast.sec06;

import coding.toast.common.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class Lec03HotPublisherAutoConnect {
    public static void main(String[] args) {
        
        // var movieFlux = movieStream().publish().autoConnect(); // minimum subscriber default 1
        var movieFlux = movieStream().publish().autoConnect(0); // subscriber 있든 없든 ㄱㄱ
        
        Util.sleepSeconds(2);
        movieFlux
                .take(4)
                .subscribe(Util.subscriber("sam"));

        Util.sleepSeconds(3);
        
        movieFlux
                .take(3)
                .subscribe(Util.subscriber("mike"));
        
        // subscriber is 0? so what? I'm going to publish anyway!
        Util.sleepSeconds(15);
    }

    private static Flux<String> movieStream() {
        return Flux.generate(() -> {
                    log.info("received the request");
                    return 1;
                }, (state, sink) -> {
                    var scene = "movie scene " + state;
                    log.info("playing {}", scene);
                    sink.next(scene);
                    return ++state;
                })
                .take(10)
                .delayElements(Duration.ofSeconds(1))
                .cast(String.class)
                ;
    }

}
