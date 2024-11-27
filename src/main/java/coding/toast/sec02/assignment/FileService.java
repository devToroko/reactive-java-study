package coding.toast.sec02.assignment;

import reactor.core.publisher.Mono;

public interface FileService {
	// read, return content
	Mono<String> read(String fileName);
	
	// create + write
	Mono<Void> write(String fileName, String content);
	
	// delete
	Mono<Void> delete(String fileName);
}
