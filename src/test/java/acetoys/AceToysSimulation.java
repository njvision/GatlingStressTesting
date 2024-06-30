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
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.9,ro;q=0.8");

  private ScenarioBuilder scn = scenario("AceToysSimulation")
    .exec(
      http("Load Home Page")
        .get("/")
              .check(status().is(200))
              .check(status().not(404), status().not(500))
              .check(substring("<h1>Ace Toys Online Store</h1>"))
              .check(css("#_csrf", "content").saveAs("csrfToken"))
    )
    .pause(2)
    .exec(
      http("Load Our Story Page")
        .get("/our-story")
              .check(regex(" toy store was founded online in \\d{4}"))
    )
    .pause(2)
    .exec(
      http("Load Get in Touch Page")
        .get("/get-in-touch")
              .check(substring("Get in Touch"))
    )
    .pause(2)
    .exec(
      http("Load Product Page - Category: All")
        .get("/category/all")
    )
    .pause(2)
    .exec(
      http("Load Product Page - Category: All, Page 1")
        .get("/category/all?page=1")
    )
    .pause(2)
    .exec(
      http("Load Product Page - Category: All, Page 2")
        .get("/category/all?page=2")
    )
    .pause(2)
    .exec(
      http("Load Product Details Page - Train Toy")
        .get("/product/train-toy")
    )
    .pause(2)
    .exec(
      http("Load Product Page - Category: Kids Toys")
        .get("/category/kids-toys")
    )
    .pause(2)
    .exec(
      http("Add product to basket - ProductId: 8")
        .get("/cart/add/8")
    )
    .pause(2)
    .exec(
      http("Add product to basket - ProductId: 8")
        .get("/cart/add/8")
    )
    .pause(2)
    .exec(
      http("Add product to basket - ProductId: 15")
        .get("/cart/add/15")
    )
    .pause(2)
    .exec(
      http("View the cart")
        .get("/cart/view")
    )
    .pause(2)
    .exec(
      http("Login")
        .post("/login")
        .formParam("_csrf", "#{csrfToken}")
        .formParam("username", "user1")
        .formParam("password", "pass")
              .check(css("#_csrf", "content").saveAs("csrfTokenLoginIn"))
    )
          .exec(
                  session -> {
                    System.out.println(session);
                    System.out.println("CsrfToken: " + session.getString("csrfTokenLoginIn"));
                    return session;
                  }
          )
    .pause(2)
    .exec(
      http("Increase number of products with one item - ProductId: 8")
        .get("/cart/add/8?cartPage=true")
    )
    .pause(2)
    .exec(
      http("Increase number of products with one item - ProductId: 15")
        .get("/cart/add/15?cartPage=true")
    )
    .pause(2)
    .exec(
      http("Subtract number of products with one item - ProductId: 8")
        .get("/cart/subtract/8")
    )
    .pause(2)
    .exec(
      http("Checkout")
        .get("/cart/checkout")
    )
    .pause(2)
    .exec(
      http("Logout")
        .post("/logout")
        .formParam("_csrf", "#{csrfTokenLoginIn}")
    );

  {
	  setUp(scn.injectOpen(atOnceUsers(1))).protocols(httpProtocol);
  }
}
