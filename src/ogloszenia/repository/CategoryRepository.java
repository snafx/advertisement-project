package ogloszenia.repository;

import ogloszenia.dto.CategoryDTO;
import ogloszenia.model.CATEGORY;

import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {

    public static List<CategoryDTO> findAll() {
        List<CategoryDTO> categories = new ArrayList<>();
        categories.add(new CategoryDTO("Elektronika", "fa-gamepad", CATEGORY.ELEKTRONIKA));
        categories.add(new CategoryDTO("Motoryzacja", "fa-car", CATEGORY.MOTORYZACJA));


        return categories;
    }
}
