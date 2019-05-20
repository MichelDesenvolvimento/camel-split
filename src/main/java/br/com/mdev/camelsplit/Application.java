package br.com.mdev.camelsplit;

import br.com.mdev.camelsplit.model.Customer;
import br.com.mdev.camelsplit.route.DirectEndPointRouter;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.DefaultFluentProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * Exemplo de envio de um objeto com n informações que serão processadas
 * em paralelo
 */
public class Application {

    public static void main(String[] args) throws Exception {

        CamelContext camelContext = new DefaultCamelContext();
        camelContext.addRoutes(new DirectEndPointRouter());
        camelContext.start();

        long start = System.currentTimeMillis();

        Customer customer = new Customer("Teste");
        customer.add("A")
                .add("B")
                .add("C")
                .add("D")
                .add("E");

        DefaultFluentProducerTemplate.on(camelContext)
                .withBody(customer)
                .withHeader("token", "12345")
                .to(DirectEndPointRouter.DIRECT_START)
                .request();

        System.out.println("Tempo gasto: " + (System.currentTimeMillis() - start));
    }

}
