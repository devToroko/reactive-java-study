package coding.toast.sec03;

import coding.toast.sec01.subscriber.SubscriberImpl;
import coding.toast.sec03.helper.NameGenerator;

public class Lec07FluxVsList {
    public static void main(String[] args) {
//        var list = NameGenerator.getNamesList(10);
//        System.out.println(list);

        var subscriber = new SubscriberImpl();
        // 즉각 적인 반응 (responsive)
        NameGenerator.getNamesFlux(5)
                .subscribe(subscriber);

        // 중도 멈춤 가능
        subscriber.getSubscription().request(3);
        subscriber.getSubscription().cancel();
    }
}
