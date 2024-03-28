package org.volovich.model.home_page;

import java.util.List;
import java.util.Objects;

public class FooterColumn {
    private String columnTitle;
    private List<String> columnItems;

    public FooterColumn(String columnTitle, List<String> columnItems) {
        this.columnTitle = columnTitle;
        this.columnItems = columnItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FooterColumn that = (FooterColumn) o;
        return Objects.equals(columnTitle, that.columnTitle) && Objects.equals(columnItems, that.columnItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(columnTitle, columnItems);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Footer column title: ").append(columnTitle).append("\n");

        for (String item : columnItems) {
            stringBuilder.append("Footer column item: ").append(item).append("\n");
        }

        return stringBuilder.toString();
    }
}
