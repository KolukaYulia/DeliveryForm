import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.module.Configuration;
import java.time.Duration;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.lang.module.Configuration.*;

public class DeliveryFormTest {
    @Test
    void shouldValidForm() {
        open("http://localhost:9999/");
        //SelenideElement form = $("[data-test-id=delivery-form]");
        $("[data-test-id=city] input").setValue("Санкт-Петербург");
        $("[data-test-id=date] input").setValue("15.05.2023");
        $("[data-test-id=name] input").setValue("Петров Иван");
        $("[data-test-id=phone] input").setValue("+79041234567");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();

        $(withText("Успешно")).shouldBe(appear, Duration.ofSeconds(15));

    }
        





}
