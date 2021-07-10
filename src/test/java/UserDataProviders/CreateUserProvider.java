package UserDataProviders;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicLong;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CreateUserProvider {

	private final AtomicLong counter = new AtomicLong();

	@RequestMapping(value = "/api/users", method = RequestMethod.POST)
	public @ResponseBody Callable<String> modifyUserBody(final @RequestBody String body) {
		return new Callable<String>() {
			@SuppressWarnings("unchecked")
			public String call(){

				JSONObject builder = new JSONObject();
				builder.put("id", String.valueOf(counter.incrementAndGet()));
				builder.put("createdAt", String.valueOf(System.currentTimeMillis()));

				String userInfos[] = body.split(",");
				for (String userInfo : userInfos) {

					String keyValuesPair[] = userInfo.split(":");
					builder.put(keyValuesPair[0].replace("{", "").replace("\"", "").trim(),
							keyValuesPair[1].replace("}", "").replace("\"", "").trim());
				}

				return builder.toJSONString();
			}
		};
	}
}
