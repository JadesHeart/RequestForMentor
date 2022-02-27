package response;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * Класс с респонсом
 */
public class ApiRequest {
    /**
     * Функция респонса с сайте
     *
     * @param baseUri  базовый юрл
     * @param basePath конкретный путь к нужной странице
     * @return возвращает респонс страницы
     */
    public static Response apiRequest(String baseUri, String basePath) {
        Response response = given()
                .baseUri(baseUri)
                .contentType(ContentType.JSON)
                .when().get(basePath);
        return response;
    }
}
