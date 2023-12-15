package hello.itemservice.message;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource ms;

    @Test
    void helloMessage() {
        String result = ms.getMessage("hello", null, Locale.KOREA);
        assertThat(result).isEqualTo("안녕");
    }

    @Test
    void notFountTestCode() {
        assertThatThrownBy(() -> ms.getMessage("no code", null, null)).isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    void notFountTestCodeDefaultMessage() {
        String result = ms.getMessage("no code", null, "Default Message", null);
        assertThat(result).isEqualTo("Default Message");
    }

    @Test
    void argumentMessage() {
        String result = ms.getMessage("hello.name", new Object[]{"Spring"}, null);
        assertThat(result).isEqualTo("안녕 Spring");
    }

    @Test
    void defaultLang(){
        assertThat(ms.getMessage("hello", null, null)).isEqualTo("안녕");
        assertThat(ms.getMessage("hello", null, Locale.KOREA)).isEqualTo("안녕");
    }

    @Test
    void enLang(){
        assertThat(ms.getMessage("hello", null, Locale.ENGLISH)).isEqualTo("hello");
        assertThat(ms.getMessage("hello", null, Locale.CANADA)).isEqualTo("hello");
    }

}
