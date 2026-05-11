package com.taotao.tool.wordpick.dto.imports;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WpWordbookManifest {

    private Category category;
    private List<Chapter> chapters;

    @Getter
    @Setter
    public static class Category {

        private String name;
    }

    @Getter
    @Setter
    public static class Chapter {

        private String name;
        private String chapterFile;
    }
}
