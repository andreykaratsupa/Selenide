package ru.netology.app_card_delivery;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class app_card_delivery {
    @Test
    public void CardTest() {
        open("http://localhost:9999");
        $("span[data-test-id='city'] input").setValue("Казань");
        $("span[data-test-id='date'] input").setValue("2023_06_31");
        $("span[data-test-id='name'] input").setValue("Ким-Чан Ин");
        $("span[data-test-id='phone'] input").setValue("+79000000000");
        $("label[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $("h2").shouldBe(visible, Duration.ofSeconds(15));
    }
}
