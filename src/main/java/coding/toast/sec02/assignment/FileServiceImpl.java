package coding.toast.sec02.assignment;

import coding.toast.common.Util;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileServiceImpl implements FileService {
	@Override
	public Mono<String> read(final String fileName) {
		return Mono.fromSupplier(() -> {
			try {
				return Files.readString(Path.of(fileName));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
	}
	
	@Override
	public Mono<Void> write(String fileName, String content) {
		return Mono.<Void>fromSupplier(() -> {
			try {
				Path path = Path.of(fileName);
				if (!Files.exists(path)) {
					Files.createFile(path);
				}
				Files.writeString(Path.of(fileName), content, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			return null;
		});
	}
	
	@Override
	public Mono<Void> delete(String fileName) {
		return Mono.<Void>fromSupplier(() -> {
			try {
				Files.deleteIfExists(Path.of(fileName));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			return null;
		});
	}
	
	// test
	public static void main(String[] args) {
		String file = "C:\\Users\\sangeon\\Desktop\\reactive_programming\\reactive-java\\src\\main\\resources\\sec02\\1.txt";
		FileService fileService = new FileServiceImpl();
		fileService.write(file, "blabla")
			.subscribe(Util.subscriber());
	}
}
