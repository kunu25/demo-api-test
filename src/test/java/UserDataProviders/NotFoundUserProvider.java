package UserDataProviders;

import java.util.concurrent.Callable;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class NotFoundUserProvider {

	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public @ResponseBody Callable<String> getUser() {
		return new Callable<String>() {
			public String call() {

				return null;
			}
		};

	}
}
