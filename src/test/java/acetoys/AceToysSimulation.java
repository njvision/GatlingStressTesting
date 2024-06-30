package acetoys;

import java.time.Duration;
import java.util.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;
import io.gatling.javaapi.jdbc.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
import static io.gatling.javaapi.jdbc.JdbcDsl.*;

public class AceToysSimulation extends Simulation {

  private static String DOMAIN = "acetoys.uk";
  private HttpProtocolBuilder httpProtocol = http
    .baseUrl("https://" + DOMAIN)
    .inferHtmlResources(AllowList(), DenyList(".*\\.js", ".*\\.css", ".*\\.gif", ".*\\.jpeg", ".*\\.jpg", ".*\\.ico", ".*\\.woff", ".*\\.woff2", ".*\\.(t|o)tf", ".*\\.png", ".*detectportal\\.firefox\\.com.*"))
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.9,ro;q=0.8")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36");
  
  private Map<CharSequence, String> headers_0 = Map.ofEntries(
    Map.entry("Sec-Fetch-Dest", "document"),
    Map.entry("Sec-Fetch-Mode", "navigate"),
    Map.entry("Sec-Fetch-Site", "same-origin"),
    Map.entry("Sec-Fetch-User", "?1"),
    Map.entry("Upgrade-Insecure-Requests", "1"),
    Map.entry("sec-ch-ua", "Not/A)Brand\";v=\"8\", \"Chromium\";v=\"126\", \"Google Chrome\";v=\"126"),
    Map.entry("sec-ch-ua-mobile", "?0"),
    Map.entry("sec-ch-ua-platform", "Windows")
  );
  
  private Map<CharSequence, String> headers_8 = Map.ofEntries(
    Map.entry("Accept", "*/*"),
    Map.entry("Sec-Fetch-Dest", "empty"),
    Map.entry("Sec-Fetch-Mode", "cors"),
    Map.entry("Sec-Fetch-Site", "same-origin"),
    Map.entry("X-Requested-With", "XMLHttpRequest"),
    Map.entry("sec-ch-ua", "Not/A)Brand\";v=\"8\", \"Chromium\";v=\"126\", \"Google Chrome\";v=\"126"),
    Map.entry("sec-ch-ua-mobile", "?0"),
    Map.entry("sec-ch-ua-platform", "Windows")
  );
  
  private Map<CharSequence, String> headers_12 = Map.ofEntries(
    Map.entry("Cache-Control", "max-age=0"),
    Map.entry("Origin", "https://acetoys.uk"),
    Map.entry("Sec-Fetch-Dest", "document"),
    Map.entry("Sec-Fetch-Mode", "navigate"),
    Map.entry("Sec-Fetch-Site", "same-origin"),
    Map.entry("Sec-Fetch-User", "?1"),
    Map.entry("Upgrade-Insecure-Requests", "1"),
    Map.entry("sec-ch-ua", "Not/A)Brand\";v=\"8\", \"Chromium\";v=\"126\", \"Google Chrome\";v=\"126"),
    Map.entry("sec-ch-ua-mobile", "?0"),
    Map.entry("sec-ch-ua-platform", "Windows")
  );


  private ScenarioBuilder scn = scenario("AceToysSimulation")
    .exec(
      http("AceToysSimulation_0:GET_https://acetoys.uk/")
        .get("/")
        .headers(headers_0)
    )
    .pause(3)
    .exec(
      http("AceToysSimulation_1:GET_https://acetoys.uk/our-story")
        .get("/our-story")
        .headers(headers_0)
    )
    .pause(1)
    .exec(
      http("AceToysSimulation_2:GET_https://acetoys.uk/get-in-touch")
        .get("/get-in-touch")
        .headers(headers_0)
    )
    .pause(25)
    .exec(
      http("AceToysSimulation_3:GET_https://acetoys.uk/category/all")
        .get("/category/all")
        .headers(headers_0)
    )
    .pause(2)
    .exec(
      http("AceToysSimulation_4:GET_https://acetoys.uk/category/all?page=1")
        .get("/category/all?page=1")
        .headers(headers_0)
    )
    .pause(2)
    .exec(
      http("AceToysSimulation_5:GET_https://acetoys.uk/category/all?page=2")
        .get("/category/all?page=2")
        .headers(headers_0)
    )
    .pause(3)
    .exec(
      http("AceToysSimulation_6:GET_https://acetoys.uk/product/train-toy")
        .get("/product/train-toy")
        .headers(headers_0)
    )
    .pause(6)
    .exec(
      http("AceToysSimulation_7:GET_https://acetoys.uk/category/kids-toys")
        .get("/category/kids-toys")
        .headers(headers_0)
    )
    .pause(4)
    .exec(
      http("AceToysSimulation_8:GET_https://acetoys.uk/cart/add/8")
        .get("/cart/add/8")
        .headers(headers_8)
    )
    .pause(6)
    .exec(
      http("AceToysSimulation_9:GET_https://acetoys.uk/cart/add/8")
        .get("/cart/add/8")
        .headers(headers_8)
    )
    .pause(2)
    .exec(
      http("AceToysSimulation_10:GET_https://acetoys.uk/cart/add/15")
        .get("/cart/add/15")
        .headers(headers_8)
    )
    .pause(2)
    .exec(
      http("AceToysSimulation_11:GET_https://acetoys.uk/cart/view")
        .get("/cart/view")
        .headers(headers_0)
    )
    .pause(10)
    .exec(
      http("AceToysSimulation_12:POST_https://acetoys.uk/login")
        .post("/login")
        .headers(headers_12)
        .formParam("_csrf", "a2fddb46-95ad-4f3f-b189-549299b13168")
        .formParam("username", "user1")
        .formParam("password", "pass")
    )
    .pause(4)
    .exec(
      http("AceToysSimulation_13:GET_https://acetoys.uk/cart/add/8?cartPage=true")
        .get("/cart/add/8?cartPage=true")
        .headers(headers_0)
    )
    .pause(2)
    .exec(
      http("AceToysSimulation_14:GET_https://acetoys.uk/cart/add/15?cartPage=true")
        .get("/cart/add/15?cartPage=true")
        .headers(headers_0)
    )
    .pause(2)
    .exec(
      http("AceToysSimulation_15:GET_https://acetoys.uk/cart/subtract/8")
        .get("/cart/subtract/8")
        .headers(headers_0)
    )
    .pause(8)
    .exec(
      http("AceToysSimulation_16:GET_https://acetoys.uk/cart/checkout")
        .get("/cart/checkout")
        .headers(headers_0)
    )
    .pause(2)
    .exec(
      http("AceToysSimulation_17:POST_https://acetoys.uk/logout")
        .post("/logout")
        .headers(headers_12)
        .formParam("_csrf", "42100910-de0b-42c1-b224-ba17d8cb0f0b")
    );

  {
	  setUp(scn.injectOpen(atOnceUsers(1))).protocols(httpProtocol);
  }
}
