package SudokuPackage;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;
import com.google.gson.Gson;

public class SudokuHandler implements RequestHandler<
        APIGatewayV2HTTPEvent, APIGatewayV2HTTPResponse> {

    private final Gson gson = new Gson();

    @Override
    public APIGatewayV2HTTPResponse handleRequest(
            APIGatewayV2HTTPEvent event, Context context) {

        SudokuGenerator generator = new SudokuGenerator();
        int[][] board = generator.generateBoard();

        APIGatewayV2HTTPResponse response = new APIGatewayV2HTTPResponse();
        response.setStatusCode(200);
        response.setBody(gson.toJson(board));
        response.setHeaders(
                java.util.Map.of("Content-Type", "application/json")
        );

        return response;
    }
}