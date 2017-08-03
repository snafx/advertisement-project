package ogloszenia.dto;


import ogloszenia.model.CATEGORY;

/**
 * klasa - wrapper ktory umozliwi ładne generowanie i prezentowanie pojedynczej kategorii
 * DTO - data transfer object
 */

public class CategoryDTO {
    String name;
    String iconName;
    CATEGORY category;

    public CategoryDTO(String name, String iconName, CATEGORY category) {
        this.name = name;
        this.iconName = iconName;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public CATEGORY getCategory() {
        return category;
    }

    public void setCategory(CATEGORY category) {
        this.category = category;
    }
}
