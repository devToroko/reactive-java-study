package coding.toast.sec02;

import lombok.extern.slf4j.Slf4j;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;

@Slf4j
public class Lec12Assignment {
    public static void main(String[] args) {
        // var fileService = new FileServiceImpl2();
        // fileService.write("file.txt", "This is a test file")
        //     .subscribe(Util.subscriber());
        //
        // fileService.read("file.txt")
        //     .subscribe(Util.subscriber());
        
        HttpURLConnection urlConnection = null;
	    try {
		    URL url = URI.create("https://jsonplaceholder.typicode.com/posts").toURL();
            urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setReadTimeout(3000);
            urlConnection.setConnectTimeout(3000);
            urlConnection.setInstanceFollowRedirects(true);
            // urlConnection.connect();
		    
		    try(OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream(), StandardCharsets.UTF_8);
		        BufferedWriter bufferedWriter = new BufferedWriter(out)){
                bufferedWriter
                    .write(
                    """
                    {"title": "foo", "body": "bar", "userId": 1}""");
			    bufferedWriter.flush();
            };
            
            System.out.println(urlConnection.getResponseMessage());
		    String result = new String(urlConnection.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
		    System.out.println("result = " + result);
		    
	    } catch (IOException e) {
		    throw new RuntimeException(e);
	    } finally {
            if (urlConnection != null) {
	            urlConnection.disconnect();
	            try {urlConnection.getInputStream().close();} catch (IOException e) {/*ignore*/}
	            try {urlConnection.getOutputStream().close();} catch (IOException e) {/*ignore*/}
            }
        }
		
	    try (HttpClient httpClient = HttpClient.newHttpClient()) {
		    HttpResponse<String> response = httpClient.send(
			    HttpRequest.newBuilder().GET().uri(URI.create("https://jsonplaceholder.typicode.com/posts/1"))
				    .timeout(Duration.of(5L, SECONDS)).build(),
			    HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
		    
		    System.out.println("code : " + response.statusCode());
		    System.out.println(response.body());
	    } catch (IOException | InterruptedException e) {
		    throw new RuntimeException(e);
	    }
		
    }
}
