package ogloszenia.repository;

import ogloszenia.dto.CategoryDTO;
import ogloszenia.model.CATEGORY;

import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {

    /*
	 * ELEKTRONIKA,MOTORYZACJA,NIERUCHOMOSCI,MODA,SPORT_I_REKREACJA,MUZYKA_I_EDUKACJA,USLUGI,PRACA
	 */

    public static List<CategoryDTO> findAll() {
        List<CategoryDTO> categories = new ArrayList<>();
        categories.add(new CategoryDTO("Elektronika", "fa-gamepad", CATEGORY.ELEKTRONIKA));
        categories.add(new CategoryDTO("Motoryzacja", "fa-car", CATEGORY.MOTORYZACJA));


        return categories;
    }


    //pobrac nazwe kategorii na podstawie enuma
    //pobierzemy i utworzymy obiekt CategoryDTO
    public static CategoryDTO findByCategory(CATEGORY category) {
        return findAll().stream()
                .filter(e -> e.getCategory().equals(category))
                .findFirst()
                .get(); //bo optional
    }
}
