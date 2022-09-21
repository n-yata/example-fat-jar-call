package example.micronaut;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;

@MicronautTest
public class FunctionRequestHandlerTest {

    @Inject
    private FunctionRequestHandler handler;

    @BeforeEach
    void beforeEach() {
    }

    @Test
    public void testHandler() {

        /* 期待値 */
        String expected = "{\"findKey\":\"testName\",\"pass\":\"pass\",\"id\":1,\"HTTPMethd\":\"GET\"}";

        /* 引数設定 */
        APIGatewayProxyRequestEvent request = new APIGatewayProxyRequestEvent();
        request.setHttpMethod("GET");
        request.setPath("/");

        JSONObject body = new JSONObject();
        body.put("id", 1);
        body.put("pass", "pass");
        body.put("findKey", "testName");
        request.setBody(body.toString());

        /* 呼び出し */
        APIGatewayProxyResponseEvent response = handler.execute(request);

        /* 判定 */
        assertEquals(200, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }
}
