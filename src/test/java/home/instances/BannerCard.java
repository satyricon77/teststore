package home.instances;

import java.util.Objects;

public class BannerCard {
    private String header;
    private String subHeader;
    private String description;

    public BannerCard(String header, String subHeader, String description) {
        this.header = header;
        this.subHeader = subHeader;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BannerCard bannerCard = (BannerCard) o;
        return Objects.equals(header, bannerCard.header) && Objects.equals(subHeader, bannerCard.subHeader) &&
                Objects.equals(description, bannerCard.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, subHeader, description);
    }

    @Override
    public String toString() {
        return "Banner header: " + header + "\n" +
                "Banner sub-header: " + subHeader + "\n" +
                "Banner description: " + description;
    }
}
