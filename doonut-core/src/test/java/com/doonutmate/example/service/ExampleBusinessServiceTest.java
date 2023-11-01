package com.doonutmate.example.service;

import com.doonutmate.example.model.Example;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ExampleBusinessServiceTest {

    @Autowired
    private ExampleBusinessService service;

    @Test
    void create() {
        // given
        var example = new Example(null, "name");

        // when
        var savedEntityId = service.create(example);

        // then
        var saved = service.get(savedEntityId);
        assertThat(saved.name()).isEqualTo(example.name());
    }

    @Test
    void get() {
        // given
        var example = new Example(null, "name");
        var savedEntityId = service.create(example);

        // when
        var actual = service.get(savedEntityId);

        // then
        assertThat(actual)
                .extracting("id", "name")
                .containsExactly(savedEntityId, "name");
    }
}
