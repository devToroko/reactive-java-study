package coding.toast.sec02;

import coding.toast.common.Util;
import coding.toast.sec02.client.ExternalServiceClient;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
public class Lec11NonBlockingIO {
    public static void main(String[] args) {
	    var client = new ExternalServiceClient();
        log.info("starting");
        
        for (int i = 1; i <= 100; i++) {
            client.getProductName(i)
                .block();
                // .subscribe(Util.subscriber());
        }
        
        Util.sleepSeconds(2);
    }
}
