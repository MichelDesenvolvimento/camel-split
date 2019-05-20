package br.com.mdev.camelsplit.route;

import br.com.mdev.camelsplit.model.Customer;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;

/**
 * Exemplo de rota utilizando split com processamento paralelo
 */
public class DirectEndPointRouter extends RouteBuilder {

    private static final String DIRECT_END = "direct:end";
    public static final String DIRECT_START = "direct:start";

    @Override
    public void configure() {

        from(DIRECT_START).routeId("rota-inicial")
                .log("Chegou esta mensagem => ${body}")
                .process((exchange) -> {
                    final Message in = exchange.getIn();
                    final Customer customer = in.getBody(Customer.class);
                    final Message out = exchange.getOut();
                    Object token = in.getHeaders().get("token");
                    out.setHeader("token", token);
                    out.setBody(customer.getContracts());
                })
                .split(simple("${body}"))
                .parallelProcessing()
                .to(DIRECT_END);


        from(DIRECT_END).routeId("rota-final")
                .delayer(500)
                .log("Chegou => ${body} ${headers}");
    }
}