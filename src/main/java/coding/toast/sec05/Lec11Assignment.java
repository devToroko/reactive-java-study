package coding.toast.sec05;

import coding.toast.common.Util;
import coding.toast.sec05.assignment.ProductServiceClient;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lec11Assignment {

    public static void main(String[] args) {
        var client = new ProductServiceClient();
        // only 1 ~ 4
        client.getProductName(4)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(5);
    }
}
