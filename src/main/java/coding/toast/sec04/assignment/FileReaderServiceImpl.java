package coding.toast.sec04.assignment;

import coding.toast.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReaderServiceImpl implements FileReaderService {
	
	private static final Logger log = LoggerFactory.getLogger(FileReaderServiceImpl.class);
	
	@Override
	public Flux<String> read(Path path) {
		return Flux.generate(
			() -> newFileReader(path),
			(br, sink) -> {
				int content;
				try {
					content = br.read();
					if (content == -1) {
						sink.complete();
					} else {
						sink.next(String.valueOf((char)content));
					}
				} catch (IOException e) {
					sink.error(e);
				}
				return br;
			}, this::closeFileReader);
		
		
	}
	
	private BufferedReader newFileReader(Path path) throws IOException {
		return Files.newBufferedReader(path, StandardCharsets.UTF_8);
	}
	
	private void closeFileReader(Reader reader) {
		try {
			reader.close();
		} catch (IOException e) {
			log.info("ignore closing error, error msg : {}", e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		String fileName = "src\\main\\resources\\sec02\\1.txt";
		FileReaderServiceImpl fileReaderService = new FileReaderServiceImpl();
		fileReaderService.read(Path.of(fileName))
			.subscribe(Util.subscriber());
	}
}
