//package coding.toast.sec02;
//
//import coding.toast.common.Util;
//import lombok.extern.slf4j.Slf4j;
//import reactor.core.publisher.Mono;
//
//@Slf4j
//public class Lec09PublisherCreateVsExecution {
//    public static void main(String[] args) {
//
//    }
//
//    private static Mono<String> getName() {
//        log.info("entered the method");
//        return Mono.fromSupplier(() -> {
//            log.info("generating");
//            return Util.faker().name().firstName();
//        });
//    }
//}
