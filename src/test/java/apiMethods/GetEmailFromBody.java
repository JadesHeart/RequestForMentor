package apiMethods;

import static properties.PropertiesReader.getProperies;
import static response.ApiRequest.apiRequest;

import io.restassured.response.Response;

import java.io.IOException;

/**
 * Класс с получением майла
 */
public class GetEmailFromBody {
    /**
     * Метод для получения мэйла с АПИшки
     * 1) получаю респонс страницы
     * 2) в переменную stringResponse засовываю почту юзера
     *
     * @param fistName имя
     * @param lastName фамилия
     * @return возвращает найденную почту
     */
    public static String getEmailFromBody(String fistName, String lastName) throws IOException {
        Response pageResponse = apiRequest(getProperies("baseURL"), getProperies("basePATH"));
        String stringResponse =
                pageResponse.jsonPath()
                        .getString("data.find{(it.first_name=='" + fistName + "')&&" +
                                "(it.last_name =='" + lastName + "')}.email");
        return stringResponse;
    }
}
