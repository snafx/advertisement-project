package ogloszenia.repository;

import ogloszenia.model.CATEGORY;
import ogloszenia.model.dto.CategoryDTO;

import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {

    public static List<CategoryDTO> findAll() {
        List<CategoryDTO> categories = new ArrayList<>();
        categories.add(new CategoryDTO("Elektronika", "fa-gamepad", CATEGORY.ELEKTRONIKA));
        categories.add(new CategoryDTO("Motoryzacja", "fa-car", CATEGORY.MOTORYZACJA));
        categories.add(new CategoryDTO("Moda", "fa-car", CATEGORY.MODA));
        categories.add(new CategoryDTO("Praca", "fa-car", CATEGORY.PRACA));
        categories.add(new CategoryDTO("Nieruchomosci", "fa-car", CATEGORY.NIERUCHOMOSCI));
        categories.add(new CategoryDTO("Sport i Rekreacja", "fa-car", CATEGORY.SPORT_I_REKREACJA));
        categories.add(new CategoryDTO("Muzyka i Edukacja", "fa-car", CATEGORY.MUZYKA_I_EDUKACJA));
        categories.add(new CategoryDTO("Uslugi", "fa-car", CATEGORY.USLUGI));
        return categories;
    }

    //pobrac nazwe kategorii na podstawie enuma
    //pobierzemy i utworzymy obiekt CategoryDTO
    public static CategoryDTO findByCategory(CATEGORY category) {

        return findAll().stream().
                filter(e -> e.getCategory().equals(category)).
                findFirst().get(); //bo optional
    }
}
