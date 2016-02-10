package name.lcf.xrmidemo;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import name.lcf.xrmi.RemoteCallInterceptor;

import java.net.InetSocketAddress;

public class GuiceModule extends AbstractModule {

    InetSocketAddress warehouseServiceAddress;

    public GuiceModule() {
    }

    public GuiceModule(InetSocketAddress warehouseServiceAddress) {
        this.warehouseServiceAddress = warehouseServiceAddress;
    }

    public void configure() {
        if (warehouseServiceAddress != null) {
            // in this example all calls to warehouse service will be directed to the remote instance
            // however method interception could be used here, so individual methods can be distributed separately
            bindInterceptor(
                    Matchers.subclassesOf(WarehouseService.class),
                    Matchers.any(),
                    new RemoteCallInterceptor(warehouseServiceAddress)
            );
        }
    }
}
