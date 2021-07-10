package UserDataProviders;

import java.util.concurrent.Callable;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.restassured.response.Response;

@Controller
public class DeleteUserProvider {

	@RequestMapping(value = "/api/users/2", method = RequestMethod.DELETE)
	public @ResponseBody Callable<Response> deleteUser() {
		return new Callable<Response>() {

			public Response call() throws Exception {
				return null;
			}
		};
	}
}
