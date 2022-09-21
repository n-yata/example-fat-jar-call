package example.micronaut;

import org.json.JSONObject;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.micronaut.function.aws.MicronautRequestHandler;
import jakarta.inject.Inject;

public class FunctionRequestHandler
        extends MicronautRequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    @Inject
    private ObjectMapper objectMapper;

    @Inject
    Utils utils;
    @Inject
    Utils2 utils2;

    @Override
    public APIGatewayProxyResponseEvent execute(APIGatewayProxyRequestEvent input) {
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();

        JSONObject obj = new JSONObject(input.getBody());
        obj.put("HTTPMethd", input.getHttpMethod());
        obj.put("layer1", utils.call());
        obj.put("layer2", utils2.call2());

        response.setBody(obj.toString());
        response.setStatusCode(200);

        return response;
    }
}
