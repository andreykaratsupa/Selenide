package ru.netology.appCardDelivery;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class appCardDelivery {
    private String planningDate;

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    public void cardTest() {
        planningDate = generateDate(3);
        System.out.println("Planning date: " + planningDate);

        open("http://localhost:9999");
        $("span[data-test-id='city'] input").setValue("Казань");
        $("span[data-test-id='name'] input").setValue("Ким-Чан Ин");
        $("span[data-test-id='phone'] input").setValue("+79000000000");
        $("label[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();

        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);

        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
    }
}
