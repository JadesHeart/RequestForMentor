package apiMethods;

import io.restassured.response.Response;

import java.io.IOException;

import static properties.PropertiesReader.getProperies;
import static response.ApiRequest.apiRequest;

/**
 * Класс с получением майла, с использованием пагинации
 */
public class GetEmailFromPagination {
    /**
     * метод с получением почты со всем страниц
     * 1) Получаю количество страниц
     * 2)  Циклом перебераю каждую страницу: начиная с первой и на каждой ищу нужного юзера
     * 3) если нужная почта не найдена на первой странице, то переход на вторую итд
     * 4) если почты вообще нет ни на одной странице, то вернёт "Почты нет на всех страницах."
     *
     * @param fistName имя юзера
     * @param lastName фамилия юзера
     * @return почту полученную по имени и фамилии
     */
    public static String getEmailWithPagination(String fistName, String lastName) throws IOException {
        int total_page =
                apiRequest(getProperies("baseURL"), getProperies("basePATH"))
                        .jsonPath().getInt("total_pages"); // Общее количество страниц
        for (int i = 1; total_page > i - 1; ++i) {
            Response pageResponse = apiRequest(getProperies("baseURL"), getProperies("basePathWithParams") + i);//запрос к АПИшке
            String stringResponse = pageResponse.jsonPath()
                    .getString("data.find{(it.first_name=='" + fistName + "')&&" +
                            "(it.last_name =='" + lastName + "')}.email");//получаю почту
            if (stringResponse != null) {
                return stringResponse;//если почта нашлась на текущей странице то возвращает её, если нет то продолжает цикл
            }
        }
        return "Почты нет на всех страницах.";//мессендж если ничего не нашлось
    }
}
