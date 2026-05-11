package com.taotao.tool.wordpick.dto.imports;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WpWordbookData {

    private String name;
    private List<Word> words;

    @Getter
    @Setter
    public static class Word {

        private String word;
        private String phoneticUs;
        private String phoneticUk;
        private List<Sense> senses;
        private List<Example> examples;
    }

    @Getter
    @Setter
    public static class Sense {

        private String pos;
        private String meaning;
    }

    @Getter
    @Setter
    public static class Example {

        private String sentence;
        private String translation;
    }
}
