package name.lcf.xrmidemo;

import com.google.inject.Guice;
import com.google.inject.Injector;
import name.lcf.xrmi.XrmiServer;

import java.net.InetSocketAddress;

import static spark.Spark.post;

public class Application {
    public static void main(String[] args) {

        if (args.length == 0) {
            // server started without arguments, no xrmi, simply starting the webservice
            startWebServer(Guice.createInjector(new GuiceModule()));
        } else {
            String mode = args[0];
            if (mode.equals("server")) {
                // launching xrmi server only on the port specified
                int port = Integer.parseInt(args[1]);
                XrmiServer.start(Guice.createInjector(new GuiceModule()), port);

            } else if (mode.equals("client")) {
                // starting the webserver with xrmi client for the warehouse service
                String hostname = args[1];
                int port = Integer.parseInt(args[2]);
                InetSocketAddress warehouseServerAddress = new InetSocketAddress(hostname, port);

                startWebServer(Guice.createInjector(new GuiceModule(warehouseServerAddress)));
            } else {
                throw new RuntimeException("Invalid arguments passed. Either 'client' or 'server' is expected");
            }
        }
    }

    private static void startWebServer(Injector injector) {
        post("/cart", (request, response) -> {
            ShoppingCartService shoppingCartService = injector.getInstance(ShoppingCartService.class);
            String username = request.queryParams("username");
            String itemCode = request.queryParams("itemCode");
            int quantity = Integer.parseInt(request.queryParams("quantity"));
            try {
                shoppingCartService.addToCart(username, itemCode, quantity);
                response.status(200);
                return "OK";
            } catch (NotEnoughItemsException notEnoughItemsException) {
                response.status(400);
                return "FAILURE";
            }
        });
    }
}
