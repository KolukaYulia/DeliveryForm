import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.lang.module.Configuration;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.lang.module.Configuration.*;





public class DeliveryFormTest {
    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("30.05.2023"));
    }
    @Test
    void shouldValidForm() {
        String planningDate = generateDate(4);
        open("http://localhost:9999/");
        //SelenideElement form = $("[data-test-id=delivery-form]");
        $("[data-test-id=city] input").setValue("Санкт-Петербург");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Петров Петр");
        $("[data-test-id=phone] input").setValue("+79041234567");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();

        $(withText("Успешно")).shouldBe(appear, Duration.ofSeconds(15));
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);

    }
        





}
