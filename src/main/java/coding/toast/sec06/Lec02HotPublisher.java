package coding.toast.sec06;

import coding.toast.common.Util;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class Lec02HotPublisher {
    public static void main(String[] args) {

        // same as movieStream().share();
        // var movieFlux = movieStream().publish().refCount(1);  // at this point, hot publisher won't work but waits for at least one subscriber to subscribe.

        var movieFlux = movieStream().share(); // adding share! this will create hot publisher
        // log "received the request" will print only once.
        Util.sleepSeconds(2);
        log.info("sam will subscribe!");
        movieFlux
                .take(4)
                .subscribe(Util.subscriber("sam"));

        Util.sleepSeconds(3);
        log.info("mike will subscribe!");
        movieFlux
                .take(3) // 1. this will call cancel signal to upstream, but doesn't affect sam. you can not cancel hot publisher.
                .subscribe(Util.subscriber("mike"));

        // if there is no subscriber on the hot publisher the hot publisher by default it will stop automatically.
        Util.sleepSeconds(15); // because of delayElements operator
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
