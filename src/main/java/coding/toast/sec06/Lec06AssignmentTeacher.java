package coding.toast.sec06;

import coding.toast.common.Util;
import coding.toast.sec06.assignment.teacher.ExternalServiceClient;
import coding.toast.sec06.assignment.teacher.InventoryService;
import coding.toast.sec06.assignment.teacher.RevenueService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lec06AssignmentTeacher {
	
	public static void main(String[] args) {
		var client = new ExternalServiceClient();
		var inventoryService = new InventoryService();
		var revenueService = new RevenueService();
		
		client.orderStream().subscribe(inventoryService::consume);
		client.orderStream().subscribe(revenueService::consume);
		
		inventoryService.stream().subscribe(Util.subscriber("inventory"));
		revenueService.stream().subscribe(Util.subscriber("revenue"));
		
		Util.sleepSeconds(20);
	}
}

